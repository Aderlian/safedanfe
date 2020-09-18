package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancellationLetter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@OneToOne
	@JoinColumn(name = "DANFE_ID")
	@NotNull
	private Danfe danfe;
	@Column(nullable = false, name = "EVENT")
	@NotNull
	private String event;
	@Column(nullable = false, name = "JUSTIFICATION")
	@NotNull
	private String justification;
	@Column(nullable = false, name = "TYPE_EVENT")
	@NotNull
	private Long typeEvent;
	@Column(nullable = false, name = "ISSUANCE_DATE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate issuanceDate;
	@Column(nullable = false, name = "CREATION_DATE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;
	
}
