package br.com.aderliastrapazzonlange.safedanfe.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aderliastrapazzonlange.safedanfe.models.Company;
import br.com.aderliastrapazzonlange.safedanfe.models.Danfe;

public interface DanfeRepository extends JpaRepository<Danfe, Long>{
	Optional<Danfe> findByKeyNFE(String keyNFE);
	
	@Query("SELECT d FROM Danfe d WHERE d.issuanceDate BETWEEN :dateStart AND :dateEnd ORDER BY d.issuanceDate, d.numberNFE ASC")
	Iterable<Danfe> findByFilterDate(@Param("dateStart") LocalDate dateStart,
			@Param("dateEnd") LocalDate dateEnd);

	@Query("SELECT d FROM Danfe d WHERE d.company.id = :company AND d.issuanceDate BETWEEN :dateStart AND :dateEnd ORDER BY d.issuanceDate, d.numberNFE DESC")
	Iterable<Danfe> findByFilterDateCompany(@Param("dateStart") LocalDate dateStart,
			@Param("dateEnd") LocalDate dateEnd,
			@Param("company") Long company);
}
