package ru.fbtw.ctf_task.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.fbtw.ctf_task.domain.User;
import ru.fbtw.ctf_task.service.MessageService;
import ru.fbtw.ctf_task.service.UserService;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "json")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {
	String list;
	String gift;
	String ok;
	String fail;


	// создает пользователя по умолчанию
	public AppConfig(
			@Value("${def.username}") String username,
			@Value("${def.password}")String password,
			UserService userService
	) {
		User userFromDb = userService.loadUserByUsername(username);

		if (userFromDb == null) {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			userService.addUser(user);
		}
	}

	@Bean
	public MessageService messageService(){
		MessageService messageService = new MessageService();

		messageService.setList(list);
		messageService.setFail(fail);
		messageService.setGift(gift);
		messageService.setOk(ok);

		return messageService;
	}
}
