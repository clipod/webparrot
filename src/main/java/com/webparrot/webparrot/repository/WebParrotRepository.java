package com.webparrot.webparrot.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebParrotRepository extends CrudRepository<MockObject, UUID> {

	//Inmemory data store
//	private static final Map<String, MockObject> dataStore = new ConcurrentHashMap<String, MockObject>();
//
//	public synchronized Map<String, MockObject> getDatastore() {
//		return dataStore;
//	}
	
	MockObject findByUri(String uri);
	
	
}
