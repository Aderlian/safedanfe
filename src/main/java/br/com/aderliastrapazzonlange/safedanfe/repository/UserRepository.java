package br.com.aderliastrapazzonlange.safedanfe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aderliastrapazzonlange.safedanfe.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
	Optional<User> validLogin(@Param("name") String name, @Param("password") String password);
}
