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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mukesh.kb.entity.Specialization;
import com.mukesh.kb.service.SpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	
	@Autowired
	private SpecializationService service;
	//1  Show register page
	@GetMapping("/register")
	public String showRegisterPage() {
		return "SpecializationRegister";
	}
	//2  On submit save form
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization spec, Model model) {
		Long id = service.saveSpecialization(spec);
		String message = "One Specialization with ID : "+id+" is ceated";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}
	//3  View All Data
	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "messages", required = false) String message ) {
		List<Specialization> list = service.getAllSpecializations();
		model.addAttribute("list", list);
		model.addAttribute("messages",message);
		return "SpecializationData";
	}
		
	//4 Delete one Specialization by ID
	@GetMapping("/delete")
	public String deleteByID(@RequestParam Long id, RedirectAttributes attributes) {
		service.removeSpecialization(id);
		attributes.addAttribute("messages", "Specialization with ID : "+id+" is removed");
		return "redirect:all";
	}
	//5 Get edit page by id
	@GetMapping("/edit")
	public String editById(@RequestParam Long id, Model model) {
		Specialization spec = service.getOneSpecialization(id);
		model.addAttribute("spec", spec);
		return "SpecializationEdit";
	}
	
	//6 Update spec 
	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization spec, RedirectAttributes attributes) {
		service.updateSpecialization(spec);
		attributes.addAttribute("messages", "Specialization with Id : "+spec.getId()+" is updated");
		return "redirect:all";
	}
	
	//7 Ajax call for specCode validation
	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String specCode) {
		String resMessage = "";
		if(service.getSpecCodeCount(specCode)) {
			resMessage = specCode+" Alrady Exist";
		}
		return resMessage;
	}
	
	//8 Ajax call for specName validation
	@GetMapping("/checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String specName) {
		String resMessage = "";
		if(service.getSpecNameCount(specName)) {
			resMessage = specName+" Already Exist";
		}
		return resMessage;
	}

}
