package ru.fbtw.ctf_task.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageService {
	String list;
	String gift;
	String ok;
	String fail;

	public MessageService() {
	}
}
