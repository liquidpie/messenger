package com.vivek.rest.jersey.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.vivek.rest.jersey.messenger.database.DatabaseStub;
import com.vivek.rest.jersey.messenger.model.Comment;
import com.vivek.rest.jersey.messenger.model.ErrorMessage;
import com.vivek.rest.jersey.messenger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseStub.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(long messageId, long commentId) {
		Message message = messages.get(messageId);
		if (message == null) {
			ErrorMessage error = new ErrorMessage();
			error.setErrorMessage("Message Id not found. BAD REQUEST");
			error.setErrorCode(400);
			error.setDocumentation("http://github.com/liquidpie/messenger");
			Response response = Response.status(Status.BAD_REQUEST)
							.entity(error)
							.build();
			
			throw new WebApplicationException(response);
		}
		Map<Long, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			ErrorMessage error = new ErrorMessage("Comment Id not found", 404, "http://github.com/liquidpie/messenger");
			Response response = Response.status(Status.NOT_FOUND)
							.entity(error)
							.build();
			throw new NotFoundException(response);
		}
		return comment;
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if (comment.getId() == 0) {
			comment.setId(comments.size() + 1);
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
	
}
