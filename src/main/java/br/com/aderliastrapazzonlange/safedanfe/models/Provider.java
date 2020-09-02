package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provider implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(nullable = false, name = "NAME")
	private String name;
	@Column(nullable = false, name = "CNPJ")
	private String cnpj;
	@Column(nullable = false, name = "CITY")
	private String city;
	@Column(nullable = false, name = "ESTADO")
	private String estado;

	public Provider() {

	}

	public Provider(String name, String cnpj, String city, String municipio, String estado) {

		this.name = name;
		this.cnpj = cnpj;
		this.city = city;
		this.estado = estado;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
