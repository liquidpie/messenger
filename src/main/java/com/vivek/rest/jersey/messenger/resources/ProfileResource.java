package com.vivek.rest.jersey.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vivek.rest.jersey.messenger.model.Profile;
import com.vivek.rest.jersey.messenger.service.ProfileService;

@Path("/profiles")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProfileResource {
	
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles() {
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{name}")
	public Profile getProfile(@PathParam("name") String name) {
		return profileService.getProfile(name);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	@DELETE
	@Path("/{name}")
	public void removeProfile(@PathParam("name") String name) {
		profileService.removeProfile(name);
	}
	
	@PUT
	@Path("/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Profile updateMessage(@PathParam("name") String name, Profile profile) {
		profile.setProfileName(name);
		return profileService.updateProfile(profile);
	}

}
