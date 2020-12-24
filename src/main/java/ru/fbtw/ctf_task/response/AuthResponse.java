package ru.fbtw.ctf_task.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
	int code;
	String message;
	String token;

	public AuthResponse(int code, String message, String token) {
		this.code = code;
		this.message = message;
		this.token = token;
	}
}
