package ru.timestop.route.trace.exception;

/**
 * @author t.i.m.e.s.t.o.p
 * @version 1.0.0
 * @since 27.07.2018.
 */
public class TraceServiceException extends Exception {
    public TraceServiceException(String msg){
        super(msg);
    }

    public TraceServiceException(){
        super();
    }

    public TraceServiceException(Throwable e){
        super(e);
    }
}
