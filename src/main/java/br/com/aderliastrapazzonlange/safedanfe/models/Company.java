package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, name = "FANTASY_NAME")
	@NotEmpty(message = "{name.required}")
	private String fantasyName;
	@Column(nullable = false, name = "CNPJ")
	@NotEmpty(message = "{cnpj.required}")
	private String cnpj;
	@Column(nullable = false, name = "FILE_PATH")
	@NotEmpty(message = "{dir.required}")
	private String filePath;

}
