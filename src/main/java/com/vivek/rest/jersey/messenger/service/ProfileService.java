package com.vivek.rest.jersey.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vivek.rest.jersey.messenger.database.DatabaseStub;
import com.vivek.rest.jersey.messenger.model.Profile;

public class ProfileService {

	private static Map<String, Profile> profiles = DatabaseStub.getProfiles();
	
	public ProfileService() {
		profiles.put("vivek", new Profile(1L, "vivek", "Vivek", "Jaiswal"));
		profiles.put("sonia", new Profile(2L, "sonia", "Sonia", "Dey"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty())
			return null;
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
