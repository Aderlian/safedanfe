package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Danfe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@ManyToOne
	private Company company;
	@ManyToOne
	private Provider provider;
	@Column(nullable = false, name = "NUMBER_NFE")
	private Long numberNFE;
	@Column(nullable = false, name = "KEY_NFE")
	private String keyNFE;
	@Column(nullable = false, name = "COMPANY")
	private BigDecimal amount;
	@Column(nullable = false, name = "ISSUANCE_DATE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate issuanceDate;
	@Column(nullable = false, name = "CREATION_DATE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;

	public Danfe() {
		this.company = new Company();
		this.provider = new Provider();
	}

	public Danfe(Company company, Provider provider, Long numberNFE, String keyNFE, BigDecimal amount,
			LocalDate issuanceDate, LocalDate creationDate) {

		this.company = company;
		this.provider = provider;
		this.numberNFE = numberNFE;
		this.keyNFE = keyNFE;
		this.amount = amount;
		this.issuanceDate = issuanceDate;
		this.creationDate = creationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Long getNumberNFE() {
		return numberNFE;
	}

	public void setNumberNFE(Long numberNFE) {
		this.numberNFE = numberNFE;
	}

	public String getKeyNFE() {
		return keyNFE;
	}

	public void setKeyNFE(String keyNFE) {
		this.keyNFE = keyNFE;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(LocalDate issuanceDate) {
		this.issuanceDate = issuanceDate;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

}
