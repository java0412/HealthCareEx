package com.mukesh.kb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mukesh.kb.entity.Specialization;
import com.mukesh.kb.service.SpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private SpecializationService service;
	// Show register page
	@GetMapping("/register")
	public String showRegisterPage() {
		return "SpecializationRegister";
	}
	// On submit save form
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization spec, Model model) {
		Long id = service.saveSpecialization(spec);
		String message = "One Specialization with ID : "+id+" is ceated";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	// View All Data
	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message ) {
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		return "SpecializationData";
	}
		
	// Delete one Specialization by ID
	@GetMapping("/delete")
	public String deleteByID(@RequestParam Long id, RedirectAttributes attributes) {
		service.removeSpecialization(id);
		attributes.addAttribute("messages", "Specialization with ID : "+id+" is removed");
		return "redirect:all";
	}
	// Get edit page by id
	@GetMapping("/edit")
	public String editById(@RequestParam Long id, Model model) {
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("spec", spec);
		return "SpecializationEdit";
	}
	
	// Update spec 
	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization spec, RedirectAttributes attributes) {
		service.updateSpecialization(spec);
		attributes.addAttribute("messages", "Specialization with Id : "+spec.getId()+" is updated");
		return "redirect:all";
	}

}
