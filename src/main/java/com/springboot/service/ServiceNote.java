package com.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.springboot.bean.Note;
import com.springboot.dao.DaoNote;

@Service
public class ServiceNote implements iServiceNote {
	
	@Autowired
	DaoNote daonote;

	@Transactional(readOnly = true)
	@Override
	public List<Note> rechercheNote() {
		// TODO Auto-generated method stub
		return daonote.findAll();
	}

	@Override
	public Note rechercheNoteId(int id) {
		// TODO Auto-generated method stub
		return daonote.findById(id).get();
	}

	@Override
	public void creerNote(Note pnote) {
		daonote.save(pnote);
		
	}

	@Override
	public void modifierNote(Note pnote) {
		// TODO Auto-generated method stub
		daonote.save(pnote);
	}

	@Override
	public void supprimerNote(Note pnote) {
		// TODO Auto-generated method stub
	daonote.deleteById(pnote.getId());	
	}
	
	
	
	
	

}
