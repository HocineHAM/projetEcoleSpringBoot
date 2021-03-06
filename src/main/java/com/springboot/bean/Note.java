package com.springboot.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_notes")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idnotes")
	private Integer id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_saisie;
	@ManyToOne
	@JoinColumn(name = "ideleve")
	private Eleve eleve;

	@ManyToOne
	@JoinColumn(name = "idclasse")
	private Classe classe;
	@ManyToOne
	@JoinColumn(name = "idmatiere")
	private Matiere matiere;
	@ManyToOne
	@JoinColumn(name = "idprof")
	private Professeur prof;
	
	private Integer idtrimestre;
	private Integer note;
	@Lob
	@Column(name="avis", columnDefinition = "TEXT")
	private String avis;
	private float avancement;

	public Classe getClasse() {
		return classe;
	}

	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public Matiere getMatiere() {
		return matiere;
	}

	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}

	public Professeur getProf() {
		return prof;
	}

	public void setProf(Professeur prof) {
		this.prof = prof;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate_saisie() {
		return date_saisie;
	}

	public void setDate_saisie(Date date_saisie) {
		this.date_saisie = date_saisie;
	}

	public Integer getIdtrimestre() {
		return idtrimestre;
	}

	public void setIdtrimestre(Integer idtrimestre) {
		this.idtrimestre = idtrimestre;
	}

	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}

	public String getAvis() {
		return avis;
	}

	public void setAvis(String avis) {
		this.avis = avis;
	}

	public float getAvancement() {
		return avancement;
	}

	public void setAvancement(float avancement) {
		this.avancement = avancement;
	}

}
