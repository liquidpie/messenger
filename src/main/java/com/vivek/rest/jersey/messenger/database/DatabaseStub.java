package com.vivek.rest.jersey.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.vivek.rest.jersey.messenger.model.Message;
import com.vivek.rest.jersey.messenger.model.Profile;

public class DatabaseStub {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

}
