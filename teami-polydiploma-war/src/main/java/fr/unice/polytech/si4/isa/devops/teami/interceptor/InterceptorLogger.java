package fr.unice.polytech.si4.isa.devops.teami.interceptor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InterceptorLogger implements Serializable{
    private static final Logger log = Logger.getLogger(InterceptorLogger.class.getName());

    @AroundInvoke
    public Object methodLog(InvocationContext ctx) throws Exception {
        String id = ctx.getTarget().getClass().getSimpleName() + "::" + ctx.getMethod().getName();
        String parametersString = Arrays.asList(ctx.getParameters()).toString();
        log.info("*** InterceptorLogger intercepts " + id + " with parameters "+ parametersString);
        try {
            return ctx.proceed();
        } finally {
            log.info("*** End of interception for" + id);
        }
    }
}