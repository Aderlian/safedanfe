package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Danfe implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	@ManyToOne
	@JoinColumn(name = "ID_COMPANY")
	@NotNull
	private Company company;
	@ManyToOne
	@JoinColumn(name = "ID_PROVIDER")
	@NotNull
	private Provider provider;
	@Column(nullable = false, name = "NUMBER_NFE")
	@NotNull
	private Long numberNFE;
	@Column(nullable = false, name = "KEY_NFE")
	@NotEmpty
	private String keyNFE;
	@Column(nullable = false, name = "AMOUNT")
	@NotNull
	private BigDecimal amount;
	@OneToOne
	@JoinColumn(name = "CANCELLATION_LETTER_ID")
	private CancellationLetter cancellationLetter;
	@Column(nullable = false, name = "ISSUANCE_DATE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate issuanceDate;
	@Column(nullable = false, name = "CREATION_DATE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;
	
	@PrePersist
	public void prePersist() {
		setCreationDate(LocalDate.now());
	}
}
