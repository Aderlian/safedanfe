package br.com.aderliastrapazzonlange.safedanfe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;

public interface DanfeRepository extends JpaRepository<Danfe, Long>{
	Optional<Danfe> findByKeyNFE(String keyNFE);

}
