package com.springboot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.bean.Classe;
import com.springboot.bean.Eleve;
import com.springboot.bean.Matiere;
import com.springboot.bean.Note;
import com.springboot.bean.Professeur;
import com.springboot.controller.form.NoteForm;
import com.springboot.service.IServiceMatiere;
import com.springboot.service.iServiceClasse;
import com.springboot.service.iServiceEleve;
import com.springboot.service.iServiceNote;
import com.springboot.service.iServiceProfesseur;

@Controller
public class NoteController {

	@Autowired
	private iServiceNote servicenote;
	@Autowired
	private iServiceClasse serviceclasse;

	@Autowired
	private iServiceEleve serviceeleve;

	@Autowired
	private iServiceProfesseur serviceprofesseur;

	@Autowired
	private IServiceMatiere servicematiere;

	private Note convertForm(NoteForm noteform) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Float fl = Float.parseFloat(noteform.getAvancement());
		Professeur prof = serviceprofesseur.rechercheProfId(Integer.valueOf(noteform.getProf()));
		Matiere mat = servicematiere.rechercheMatiereId(Integer.valueOf(noteform.getMatiere()));
		Date madate = sdf.parse(noteform.getDate_saisie());
		Note note = new Note();
		Eleve eleve= serviceeleve.rechercheEleveId(Integer.valueOf(noteform.getEleve()));
		Classe clas = serviceclasse.rechercheClasseId(Integer.valueOf(noteform.getClasse()));
		Integer nt = Integer.parseInt(noteform.getNote());
		
		note.setMatiere(mat);
		note.setNote(Integer.parseInt(noteform.getNote()));
		note.setId(noteform.getId());
		note.setProf(prof);
		note.setNote(nt);
		note.setDate_saisie(madate);
		note.setAvis(noteform.getAvis());
		note.setAvancement(fl);
		note.setClasse(clas);
		note.setIdtrimestre(1);
		note.setEleve(eleve);
		
		return note;

	}

	@GetMapping("/afficherCreerNote")
	public String getAffiche(Model pmodel) {
		List<Note> lnote = servicenote.rechercheNote();
		pmodel.addAttribute("listenotes", lnote);
		List<Professeur> lprof = serviceprofesseur.rechercheProf();
		List<Matiere> lmatiere = servicematiere.rechercheMatiere();
		List<Eleve> leleve = serviceeleve.rechercheEleve();
		List<Classe> lclasse = serviceclasse.rechercheClasse();
		pmodel.addAttribute("listeClasse", lclasse);
		pmodel.addAttribute("listematieres", lmatiere);
		pmodel.addAttribute("listeprofs", lprof);
		pmodel.addAttribute("listeEleves", leleve);
		pmodel.addAttribute("action", "CreerNote");
		if (pmodel.containsAttribute("noteform") == false) {
			NoteForm noteform = new NoteForm();
			noteform.setId(0);
			pmodel.addAttribute("noteform", noteform);
		}
		return "notes";
	}

	@GetMapping("/afficherModifierNote/{id}")
	public String getAfficheMod(@PathVariable final Integer id, Model pmodel) {
		Note note = servicenote.rechercheNoteId(id);

		pmodel.addAttribute("listenotes", null);
		pmodel.addAttribute("action", "ModifierNote");
		if (pmodel.containsAttribute("noteform") == false) {

			NoteForm noteform = new NoteForm();

			noteform.setId(note.getId());
			noteform.setDate_saisie(new SimpleDateFormat("dd-MM-yyyy").format(note.getDate_saisie()));
			noteform.setAvis(note.getAvis());

			noteform.setAvancement(String.valueOf(note.getAvancement()));
			noteform.setNote(String.valueOf(note.getNote()));

			pmodel.addAttribute("noteform", noteform);
		}
		return "notes";
	}

	@GetMapping("/SupprimerNote/{id}")
	public String getSupprimer(@PathVariable final Integer id, Model pmodel) {
		Note note = servicenote.rechercheNoteId(id);
		if (note != null) {
			servicenote.supprimerNote(note);
		}
		return this.getAffiche(pmodel);
	}

	@PostMapping("/CreerNote")
	public String ajoutNote(@Valid @ModelAttribute NoteForm noteform, BindingResult presult, Model pmodel) {
		System.err.println(presult);
		if (!presult.hasErrors()) {
			try {
				System.err.println(noteform);
				Note note = convertForm(noteform);
				servicenote.creerNote(note);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return this.getAffiche(pmodel);
	}

	@PostMapping("/ModifierNote")
	public String modifieNote(@Valid @ModelAttribute NoteForm noteform, BindingResult presult, Model pmodel) {
		if (!presult.hasErrors()) {
			try {
				Note note = convertForm(noteform);
				servicenote.modifierNote(note);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return this.getAffiche(pmodel);
	}

}
