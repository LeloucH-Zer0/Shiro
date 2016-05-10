package com.snapdeal.qaas.shiro.authenticator;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminAuthenticator
{
    @AroundInvoke
    public Object authenticate(InvocationContext context) throws Exception{
    	Logger LOG = LoggerFactory.getLogger(AdminAuthenticator.class);
    	LOG.info("Entered Authenticator");
        if (!SecurityUtils.getSubject().hasRole("admin")){
            StringBuilder exBuilder = new StringBuilder();
            exBuilder.append("User: \'");
            exBuilder.append(SecurityUtils.getSubject().getPrincipal().toString());
            exBuilder.append("\' is not authorized to use this resource");
            LOG.info("\n\n YOU DO NOT HAVE ANY RIGHT TO ACCESS THIS !!!! \n\n");
            throw new SecurityException(exBuilder.toString());
        }
        LOG.info("\n\n YOU HAVE THE RIGHT TO ACCESS THIS !!!! \n\n");
        return context.proceed();
    }
}