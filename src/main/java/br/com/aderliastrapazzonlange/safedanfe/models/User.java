package br.com.aderliastrapazzonlange.safedanfe.models;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length=150, name = "NAME")
	@NotEmpty(message = "{name.required}")
	private String name;
	@Column(nullable = false, length=150, name = "PASSWORD")
	@NotEmpty(message = "{password.required}")
	private String password;
	@Column(name = "PERMISSION")
	@Enumerated(EnumType.STRING)
//	@NotEmpty(message = "{type.required}")
	private UserEnum permission;
	@Column(nullable = false, name = "CREATION_DATE", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate creationDate;
	
	@PrePersist
	public void prePersist() {
		setCreationDate(LocalDate.now());
	}

}
