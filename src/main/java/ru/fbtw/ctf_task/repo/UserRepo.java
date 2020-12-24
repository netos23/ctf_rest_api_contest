package ru.fbtw.ctf_task.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fbtw.ctf_task.domain.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findUserByUsername(String username);
}
