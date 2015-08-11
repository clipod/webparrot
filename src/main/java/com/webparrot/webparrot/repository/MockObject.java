package com.webparrot.webparrot.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity(name = "MOCKSTORE")
public class MockObject {

	@Id
	@Column(name = "MOCK_ID", columnDefinition = "CHAR")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Type(type = "uuid-char")
	private UUID mockObjectId;

	@Column(name = "URI")
	private String uri;

	@Column(name = "RESPONSE")
	@Lob
	private String response;

	@Column(name = "HEADERS")
	@Lob
	private String headers;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Map<String, String> getHeaders() {
		// return Arrays.stream(headers.split("||")).collect(Collectors.toMap(h
		// -> h.toString().split(":")[0], h -> h.toString().split(":")[1]));
		try {
			Map<String, String> headersMap;
			if (headers == null) {
				headersMap = new  HashMap<String, String>();
			} else {
				headersMap = new ObjectMapper().readValue(headers,new TypeReference<HashMap<String, String>>() {});
			}
			return headersMap;
		} catch (Exception e) {
			return new HashMap<String, String>();
		}
	}

	public void setHeaders(Map<String, String> headers) {
		try {
			this.headers = new ObjectMapper().writeValueAsString(headers);
		} catch (JsonProcessingException e) {
			System.out.println("unable to add headers");
			e.printStackTrace();
		}
	}

	public String getURI() {
		return uri;
	}

	public void setURI(String uri) {
		this.uri = uri;
	}

	public String getHeader() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public UUID getMockObjectId() {
		return mockObjectId;
	}

	public void setMockObjectId(UUID mockObjectId) {
		this.mockObjectId = mockObjectId;
	}

}
