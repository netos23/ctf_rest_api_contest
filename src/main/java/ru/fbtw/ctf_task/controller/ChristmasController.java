package ru.fbtw.ctf_task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.fbtw.ctf_task.domain.User;

import ru.fbtw.ctf_task.response.AuthResponse;
import ru.fbtw.ctf_task.security.JwtProvider;
import ru.fbtw.ctf_task.service.MessageService;
import ru.fbtw.ctf_task.service.UserService;

@RestController
public class ChristmasController {
	private final MessageService messageService;
	private final UserService userService;
	private final JwtProvider jwtProvider;

	public ChristmasController(
			MessageService messageService,
			UserService userService,
			JwtProvider jwtProvider
	) {
		this.messageService = messageService;
		this.userService = userService;
		this.jwtProvider = jwtProvider;
	}

	/*@PostMapping("/reg")
	public String register(@RequestBody User user) {
		User userFromDb = userService.loadUserByUsername(user.getUsername());

		if (userFromDb == null) {
			userService.addUser(user);
			return messageService.getOk();
		}
		return messageService.getFail();
	}*/

	@PostMapping("/auth")
	public AuthResponse auth(@RequestBody User request) {
		User user = userService.findByLoginAndPassword(request.getUsername(), request.getPassword());

		if (user != null) {
			String token = jwtProvider.generateToken(user.getUsername());
			return new AuthResponse(200, "ok", token);
		}

		return new AuthResponse(403, "Permission denied", "");
	}


	@GetMapping("/gift")
	public String gift() {
		return messageService.getGift();
	}

	@GetMapping("/list")
	public String list() {
		return messageService.getList();
	}
}
