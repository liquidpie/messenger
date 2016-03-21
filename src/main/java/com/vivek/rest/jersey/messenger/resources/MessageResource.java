package com.vivek.rest.jersey.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.vivek.rest.jersey.messenger.model.Message;
import com.vivek.rest.jersey.messenger.resources.beans.MessageFilterBean;
import com.vivek.rest.jersey.messenger.service.MessageService;

@Path("/messages")
@Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("/{id}")
	public Message getMessage(@PathParam("id") long id, @Context UriInfo uriInfo) {
		Message message = messageService.getMessage(id);
		
		message.addLink(getUriForSelf(uriInfo, message), "self");
		message.addLink(getUriForProfile(uriInfo, message), "profile");
		message.addLink(getUriForComments(uriInfo, message), "comments");
		return message;
		
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(MessageResource.class, "getCommentResource")
							.path(CommentResource.class)
							.resolveTemplate("id", message.getId())
							.build()
							.toString();
	}

	private String getUriForProfile(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
							.path(ProfileResource.class)
							.path(message.getAuthor())
							.build()
							.toString();
	}

	private String getUriForSelf(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(Long.toString(message.getId()))
							.build()
							.toString();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		String id = String.valueOf(message.getId());
		URI uriPath = uriInfo.getAbsolutePathBuilder()
								.path(id)
								.build();
		
		return Response.created(uriPath)
				.entity(message)
				.build();
		
//		return messageService.addMessage(message);
	}
	
	@DELETE
	@Path("/{id}")
	public void removeMessage(@PathParam("id") long id) {
		messageService.removeMessage(id);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("id") long id, Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	
	@Path("/{id}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}
