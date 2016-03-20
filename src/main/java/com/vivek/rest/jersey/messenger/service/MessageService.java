package com.vivek.rest.jersey.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.vivek.rest.jersey.messenger.database.DatabaseStub;
import com.vivek.rest.jersey.messenger.exception.DataNotFoundException;
import com.vivek.rest.jersey.messenger.model.Message;

public class MessageService {
	
	private static Map<Long, Message> messages = DatabaseStub.getMessages();
	
//	public List<Message> getAllMessages() {
//		Message m1 = new Message(1, "Hello World", "Vivek");
//		Message m2 = new Message(2, "Hello Jersey", "Vivek");
//		List<Message> messageList = new ArrayList<Message>();
//		messageList.add(m1);
//		messageList.add(m2);
//		return messageList;
//	}
	
	public MessageService() {
		messages.put(1L, new Message(1L, "Hello World", "Vivek Jaiswal"));
		messages.put(2L, new Message(2L, "Hello Jersey", "Vivek Jaiswal"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messagesForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message :  messages.values()) {
			cal.setTime(message.getCreatedDate());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<Message> getAllMessagesPaginated(int start, int size) {
		List<Message> messagesList = new ArrayList<Message>(messages.values());
		if (start + size > messagesList.size()) {
			return new ArrayList<Message>();
		}
		return messagesList.subList(start, start + size);
	}
	
	public Message getMessage(long id) {
		Message message = messages.get(id);
		if (message == null) {
			throw new DataNotFoundException("Message with id " + id + " not found");
		}
		return message;
	}
	
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		if (message.getId() <= 0)
			return null;
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id) {
		return messages.remove(id);
	}
	
}

