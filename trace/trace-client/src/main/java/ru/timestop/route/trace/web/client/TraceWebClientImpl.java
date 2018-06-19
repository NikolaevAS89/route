package ru.timestop.route.trace.web.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.timestop.exceptions.WebApiException;
import ru.timestop.route.trace.entities.Schedule;
import ru.timestop.route.trace.exception.NotEnoughPointsException;
import ru.timestop.route.trace.exception.TraceServiceException;
import ru.timestop.route.trace.web.TraceRequestMapping;
import ru.timestop.utilites.IOUtil;
import ru.timestop.utilites.JsonUtil;
import ru.timestop.utilites.WebUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 01.08.2018.
 */
@Component
@Qualifier("traceWebClient")
public class TraceWebClientImpl implements TraceWebClient, TraceRequestMapping {

    private static final Logger LOG = Logger.getLogger(TraceWebClientImpl.class);

    @Value("${trace.web.api.url:http://localhost:8080")
    private String url;

    @Value("${trace.web.api.user:user}")
    private String user;

    @Value("${trace.web.api.password:123456}")
    private String password;

    @Value("${trace.web.api.path:/route}")
    private String name;

    @Value("${trace.web.api.timeout:500}")
    private int timeout;

    @Override
    public Integer createRoute(List<Integer> pointIds) throws TraceServiceException, IOException {
        String message = JsonUtil.toJson(pointIds);
        if (LOG.isDebugEnabled()) {
            LOG.debug("TraceWebClientImpl(" + message + ")");
        }
        if (pointIds == null || pointIds.size() <= 1) {
            throw new NotEnoughPointsException();
        }
        String url_string = new StringBuilder()
                .append(url)
                .append(name)
                .append(TRACE_API_CREATE)
                .toString();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Request to " + url_string);
        }
        HttpURLConnection connection = null;
        try {
            URL url_addres = new URL(url_string);

            connection = (HttpURLConnection) url_addres.openConnection();
            connection.setConnectTimeout(timeout);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Basic " + WebUtil.getAuth(user, password));
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);

            DataOutputStream bw = new DataOutputStream(connection.getOutputStream());
            bw.write(message.getBytes());
            bw.flush();
            bw.close();
            if (200 == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                reader.lines().forEach(response::append);
                Integer result = Integer.parseInt(response.toString());
                LOG.info("New route with id [" + result + "] was created. ");
                return result;
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                if (418 == connection.getResponseCode()) {
                    throw JsonUtil.fromJson(reader, WebApiException.class);
                } else {
                    StringBuilder error = new StringBuilder();
                    reader.lines().forEach(error::append);
                    LOG.error(connection.getResponseMessage() + " : \n" + error.toString());
                    throw new WebApiException(connection.getResponseMessage());
                }
            }
        } catch (IOException e) {
            LOG.error(e);
            throw e;
        } finally {
            IOUtil.closeQuiet(connection);
        }
    }

    @Override
    public Shedules getRoutes() throws TraceServiceException, IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getRoutes()");
        }
        String url_string = new StringBuilder()
                .append(url)
                .append(name)
                .append(TRACE_API_GETS)
                .toString();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Request to " + url_string);
        }
        HttpURLConnection connection = null;
        try {
            URL url_addres = new URL(url_string);
            connection = (HttpURLConnection) url_addres.openConnection();
            connection.setConnectTimeout(timeout);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + WebUtil.getAuth(user, password));
            connection.setRequestProperty("Content-type", "application/json");

            if (200 == connection.getResponseCode()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                return JsonUtil.fromJson(br, Shedules.class);
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                if (418 == connection.getResponseCode()) {
                    throw JsonUtil.fromJson(reader, WebApiException.class);
                } else {
                    StringBuilder error = new StringBuilder();
                    reader.lines().forEach(error::append);
                    LOG.error(connection.getResponseMessage() + " : \n" + error.toString());
                    throw new WebApiException(connection.getResponseMessage());
                }
            }
        } catch (IOException e) {
            LOG.error(e);
            throw e;
        } finally {
            IOUtil.closeQuiet(connection);
        }
    }

    @Override
    public Shedules getRoute(Integer routeId) throws TraceServiceException, IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("getRoutes()");
        }
        String path = TRACE_API_GET.replace("{routeId}", Integer.toString(routeId));

        StringBuilder sb = new StringBuilder();
        sb.append(url).append(name).append(path);

        System.out.println(sb.toString());
        URL url_addres = new URL(sb.toString());

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url_addres.openConnection();
            connection.setConnectTimeout(timeout);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + WebUtil.getAuth(user, password));
            connection.setRequestProperty("Content-type", "application/json");
            String msg = connection.getResponseMessage();

            System.out.println(msg);
            if (!msg.equals("OK")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                StringBuilder sb1 = new StringBuilder();
                reader.lines().forEach(line -> {
                    sb1.append(line.trim());
                });
                throw new WebApiException(sb1.toString());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return JsonUtil.fromJson(br, Shedules.class);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuiet(connection);
        }
        return null;
    }

    /**
     * for parse response objects
     */
    private static final class Shedules extends ArrayList<Schedule> {
    }

    /**
     * for case when we did not use Spring framework
     */
    public static final class Builder {

        private String url = "http://localhost:8080";

        private String user;

        private String password;

        private String name = "/route";

        private int timeout = 500;

        public Builder(String user, String password) {
            this.user = user;
            this.password = password;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public TraceWebClientImpl build() {
            TraceWebClientImpl client = new TraceWebClientImpl();
            client.url = this.url;
            client.user = this.user;
            client.password = this.password;
            client.name = this.name;
            client.timeout = this.timeout;
            return client;
        }
    }
}
