package com.webparrot.webparrot.utils;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webparrot.webparrot.repository.MockObject;
import com.webparrot.webparrot.repository.WebParrotRepository;

@Component
public class ResponseBuilder {
	
	@Autowired
	private WebParrotRepository webParrotRepository;

	public Response buildResponse(String path, HttpServletResponse servletResponse, int defaultStatus) {
		
		if(webParrotRepository.findByUri(path) == null){
			//TODO: Throw proper exceptions and try to intercept.
			throw new BadRequestException();
		}
		
		MockObject object= webParrotRepository.findByUri(path);
		
		int status = (webParrotRepository.findByUri(path).getHeaders().get("status") != null)?Integer.parseInt(webParrotRepository.findByUri(path).getHeaders().get("status")):defaultStatus;
		webParrotRepository.findByUri(path).getHeaders().remove("status");
		for(Entry<String, String> savedHeaders: webParrotRepository.findByUri(path).getHeaders().entrySet()){
			servletResponse.addHeader(savedHeaders.getKey(), savedHeaders.getValue());
		}
		return Response.status(status).entity(webParrotRepository.findByUri(path).getResponse()).build();
	}
}
