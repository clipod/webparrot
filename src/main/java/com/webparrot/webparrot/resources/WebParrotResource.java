package com.webparrot.webparrot.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.webparrot.webparrot.repository.MockObject;
import com.webparrot.webparrot.repository.WebParrotRepository;
import com.webparrot.webparrot.utils.ResponseBuilder;

@Component
@Path("{paths: .+}")
public class WebParrotResource {

	@Autowired
	private ResponseBuilder responseBuilder;

	@Autowired
	private WebParrotRepository repository;

	@GET
	@Produces("*/*")
	public Response getMockData(@PathParam("paths") String path,
			@Context HttpServletResponse servletResponse) {
		return responseBuilder.buildResponse(path, servletResponse, 200);
	}

	@DELETE
	@Produces("*/*")
	public Response deleteMockData(@PathParam("paths") String path,
			@Context HttpServletResponse servletResponse) {
		return responseBuilder.buildResponse(path, servletResponse, 204);
	}

	@PUT
	@Produces("*/*")
	public Response putMockData(@PathParam("paths") String path,
			@Context HttpServletResponse servletResponse) {
		return responseBuilder.buildResponse(path, servletResponse, 200);
	}

	@HEAD
	@Produces("*/*")
	public Response headMockData(@PathParam("paths") String path,
			@Context HttpServletResponse servletResponse) {
		return responseBuilder.buildResponse(path, servletResponse, 200);
	}

	@POST
	@Produces("*/*")
	@Consumes("*/*")
	public Response insertMockData(@PathParam("paths") String path,
			String mock, @Context HttpHeaders headers,
			@Context HttpServletResponse servletResponse) {

		if (headers.getHeaderString("insert") != null && headers.getHeaderString("insert").equals("true")) {
			MockObject persistenceObject = (repository.findByUri(path) != null)?repository.findByUri(path):new MockObject();
			persistenceObject.setResponse(mock);

			Map<String, String> headersMap = new HashMap<String, String>();
			String CONTENT_TYPE = null; 
			
			for (Entry<String, List<String>> header : headers.getRequestHeaders().entrySet()) {
				if (header.getKey().matches("resp-.*")) {
					headersMap.put(header.getKey().replaceAll("resp-", ""),header.getValue().get(0));
				}
				
				CONTENT_TYPE = (header.getKey().toLowerCase().matches("content-type"))?header.getValue().get(0):"text/plain";
			}
			
			headersMap.put("Content-Type", CONTENT_TYPE);
			persistenceObject.setHeaders(headersMap);
			persistenceObject.setURI(path);
			repository.save(persistenceObject);
			return Response.noContent().build();
		} else {
			return responseBuilder.buildResponse(path, servletResponse, 201);

		}
	}
}
