package com.vivek.rest.jersey.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.vivek.rest.jersey.messenger.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorMessage(ex.getMessage());
		error.setErrorCode(00230);
		error.setDocumentation("http://github.com/liquidpie/messenger");
		return Response.status(Status.BAD_REQUEST)
						.entity(error)
						.build();
	}

}
