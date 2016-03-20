package com.vivek.rest.jersey.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.vivek.rest.jersey.messenger.model.ErrorMessage;

//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorMessage("Internal Server Error");
		error.setErrorCode(230);
		error.setDocumentation("http://github.com/liquidpie/messenger");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(error)
						.build();
	}

	
}
