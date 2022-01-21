package com.tasktrckr.api.reqresp.logging;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

@Component
public class ReqRespLoggingDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(ReqRespLoggingDispatcherServlet.class);

	private static Logger analytics = LoggerFactory.getLogger("analytics");

	@Override
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HandlerExecutionChain handler = null;
		if (!(request instanceof ContentCachingRequestWrapper)) {
			request = new ContentCachingRequestWrapper(request);
			// logger.trace(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		}
		if (!(response instanceof ContentCachingResponseWrapper)) {
			response = new ContentCachingResponseWrapper(response);
		}
		try {
			handler = getHandler(request);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getMessage(e));
			logger.error("Stack trace {}", ExceptionUtils.getStackTrace(e));
		}

		try {
			super.doDispatch(request, response);
		} catch (Exception e) {
			logger.error("{} - {}", ExceptionUtils.getMessage(e), ExceptionUtils.getStackTrace(e));
		} finally {
			if (analytics.isInfoEnabled()) {
				log(request, response, handler);
			}
			updateResponse(response);
		}
	}

	private void log(HttpServletRequest requestToCache, HttpServletResponse responseToCache,
			HandlerExecutionChain handler) {
		StringBuilder log = new StringBuilder();
		log.append("Response status: " + responseToCache.getStatus() + "~");
		log.append("Request method: " + requestToCache.getMethod() + "~");
		log.append("Request URI: " + requestToCache.getRequestURI() + "~");
		log.append("Request remote address: " + requestToCache.getRemoteAddr() + "~");
		if (handler != null) {
			log.append("Java method: " + handler.toString() + "~");
		} else {
			log.append("Java method: Handler is missing!!! ~");
		}
		log.append("Request payload: " + getRequestPayload(requestToCache));
		log.append("Response payload: " + getResponsePayload(responseToCache));
		analytics.info(log.toString());
	}

	private String getResponsePayload(HttpServletResponse response) {
		ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		if (wrapper != null) {

			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				int length = Math.min(buf.length, 102400);
				try {
					return new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					// NOOP
				}
			}
		}
		return "[unknown]";
	}

	private String getRequestPayload(HttpServletRequest request) {
		ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
		if (wrapper != null) {
			byte[] buf = wrapper.getContentAsByteArray();
			if (buf.length > 0) {
				int length = Math.min(buf.length, 102400);
				try {
					return new String(buf, 0, length, wrapper.getCharacterEncoding());
				} catch (UnsupportedEncodingException ex) {
					// NOOP
				}
			}
		}
		return "[unknown]";
	}

	private void updateResponse(HttpServletResponse response) throws IOException {
		ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response,
				ContentCachingResponseWrapper.class);
		responseWrapper.copyBodyToResponse();
	}
}
