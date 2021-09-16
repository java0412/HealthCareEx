package com.mukesh.kb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mukesh.kb.entity.Specialization;
import com.mukesh.kb.repo.SpecializationRepository;

@Service
public class SpecializationServiceImpl implements SpecializationService {
	
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		repo.deleteById(id);

	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		Optional<Specialization> opt = repo.findById(id);
		if(opt.isPresent())
			return opt.get();
		else
		return null;
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		
		repo.save(spec);

	}

}
