package com.webparrot.webparrot.exceptions;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

@Provider
@Component
public class WebParrotException implements ExceptionMapper<BadRequest> {

//	public static final Map<Class<? extends RuntimeException>, Integer> statusCodesMapping = new HashMap<Class<? extends RuntimeException>, Integer>();
//
//	static {
//		statusCodesMapping.put(BadRequest.class,Response.Status.BAD_REQUEST.getStatusCode());
//	}

	public Response toResponse(BadRequest exception) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity("Bad request message").type(MediaType.TEXT_PLAIN).build();
	}

}
