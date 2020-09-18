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
public class Provider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(nullable = false, name = "NAME")
	@NotEmpty(message = "{name.required}")
	private String name;
	@Column(nullable = false, name = "CNPJ")
	@NotEmpty(message = "{cnpj.required}")
	private String cnpj;
	@Column(nullable = false, name = "CITY")
	@NotEmpty(message = "{city.required}")
	private String city;
	@Column(nullable = false, name = "STATE")
	@NotEmpty(message = "{state.required}")
	private String state;
}
