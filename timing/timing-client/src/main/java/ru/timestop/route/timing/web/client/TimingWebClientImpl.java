package ru.timestop.route.timing.web.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.timestop.exceptions.WebApiException;
import ru.timestop.route.timing.web.TimingRequestMapping;
import ru.timestop.utilites.IOUtil;
import ru.timestop.utilites.JsonUtil;
import ru.timestop.utilites.WebUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 01.08.2018.
 */
@Component
@Qualifier("timingWebClient")
public class TimingWebClientImpl implements TimingWebClient, TimingRequestMapping {

    private static final Logger LOG = Logger.getLogger(TimingWebClientImpl.class);

    @Value("${timing.web.api.url}")
    private String url;

    @Value("${timing.web.api.user}")
    private String user;

    @Value("${timing.web.api.password}")
    private String password;

    @Value("${timing.web.api.path:/timing}")
    private String name;

    @Value("${timing.web.api.timeout:500}")
    private int timeout;

    @Override
    public Integer calcTiming(List<Integer> pointIds) throws IOException {
        String message = JsonUtil.toJson(pointIds);
        if (LOG.isDebugEnabled()) {
            LOG.debug("TimingWebClientImpl(" + message + ")");
        }
        String url_string = new StringBuilder()
                .append(url)
                .append(name)
                .append(TIMING_API_CALCULATE)
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
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Authorization", "Basic " + WebUtil.getAuth(user, password));
            connection.setDoOutput(true);

            DataOutputStream bw = new DataOutputStream(connection.getOutputStream());
            bw.write(message.getBytes());
            bw.flush();
            bw.close();
            if (200 == connection.getResponseCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                reader.lines().forEach(response::append);
                return Integer.parseInt(response.toString());
            } else {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                if (418 == connection.getResponseCode()) {
                    throw JsonUtil.fromJson(reader, WebApiException.class);
                } else {
                    StringBuilder error = new StringBuilder();
                    reader.lines().forEach(error::append);
                    LOG.error(connection.getResponseMessage() + " : \n" + error.toString());
                    throw new RuntimeException(connection.getResponseMessage());
                }
            }
        } catch (IOException e) {
            LOG.error(e);
            throw e;
        } finally {
            IOUtil.closeQuiet(connection);
        }
    }

    /**
     * for case when we did not use Spring framework
     */
    public static final class Builder {

        private String url = "http://localhost:8080";

        private String user;

        private String password;

        private String name = "/timing";

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

        public TimingWebClientImpl build() {
            TimingWebClientImpl client = new TimingWebClientImpl();
            client.url = this.url;
            client.user = this.user;
            client.password = this.password;
            client.name = this.name;
            client.timeout = this.timeout;
            return client;
        }
    }
}
