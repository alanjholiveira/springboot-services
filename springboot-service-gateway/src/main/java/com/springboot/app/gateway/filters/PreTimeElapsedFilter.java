package com.springboot.app.gateway.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


@Component
public class PreTimeElapsedFilter extends ZuulFilter {
	
	private static Logger LOGGER = LoggerFactory.getLogger(PreTimeElapsedFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		LOGGER.info("Entering post filter");
		
		Long timeStart = (Long) request.getAttribute("timeStart");
		Long timeEnd = System.currentTimeMillis();
		Long timeElapsed = timeEnd - timeStart;

		LOGGER.info(String.format("Elapsed time in seconds %s seg.", timeElapsed.doubleValue()/1000.00));
		LOGGER.info(String.format("Elapsed time in milliseg %s ms.", timeElapsed));
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
