package br.com.aderliastrapazzonlange.safedanfe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aderliastrapazzonlange.safedanfe.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByCnpj(String cnpj);

}
