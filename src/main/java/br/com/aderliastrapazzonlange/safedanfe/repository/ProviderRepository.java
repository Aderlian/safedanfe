package br.com.aderliastrapazzonlange.safedanfe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aderliastrapazzonlange.safedanfe.models.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

	Optional<Provider> findByCnpj(String cnpj);

}
