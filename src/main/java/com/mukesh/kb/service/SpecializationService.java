package com.mukesh.kb.service;

import java.util.List;

import com.mukesh.kb.entity.Specialization;

public interface SpecializationService {
	
	public Long saveSpecialization(Specialization spec);
	
	public List<Specialization> getAllSpecializations();
	
	public void removeSpecialization(Long id);
	
	public Specialization getOneSpecialization(Long id);
	
	public void updateSpecialization(Specialization spec);

}
