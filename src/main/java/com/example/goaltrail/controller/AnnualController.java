package com.example.goaltrail.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.goaltrail.entity.Annual;
import com.example.goaltrail.entity.UserInf;
import com.example.goaltrail.form.AnnualForm;
import com.example.goaltrail.repository.AnnualRepository;

@Controller
public class AnnualController {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AnnualRepository repository;

    @GetMapping("/annual/annualRegister")
    public String newAnnual(Principal principal, Model model) {
    	Authentication authentication = (Authentication) principal;
		UserInf user = (UserInf) authentication.getPrincipal();

        AnnualForm form = new AnnualForm();
        form.setUser_id(user.getUserId());
        model.addAttribute("form", form);
        
        return "annual/annualRegister";
    }

    @PostMapping("/annual/annualRegister")
    public String register(@Validated @ModelAttribute("form") AnnualForm form, Principal principal,
            BindingResult result, Model model, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            model.addAttribute("hasMessage", true);
            model.addAttribute("class", "alert-danger");
            return "annual/annualRegister";
        }

        Authentication authentication = (Authentication) principal;
		UserInf user = (UserInf) authentication.getPrincipal();
		
		Annual[] goals = {
			buildAnnualGoal(user.getUserId(), form, form.getAnnualGoal1()),
			buildAnnualGoal(user.getUserId(), form, form.getAnnualGoal2()),
			buildAnnualGoal(user.getUserId(), form, form.getAnnualGoal3()),
		};
        repository.saveAllAndFlush(Arrays.asList(goals));

        redirAttrs.addFlashAttribute("hasMessage", true);
        redirAttrs.addFlashAttribute("class", "alert-info");

        return "redirect:/annual/" + form.getYear();
    }
    
    @GetMapping("/annual/{year}")
    public String index(@PathVariable("year") int year, Principal principal, Model model) {
        Authentication authentication = (Authentication) principal;
        UserInf user = (UserInf) authentication.getPrincipal();

        List<Annual> annualGoals = repository.findAllByUserIdAndYear(user.getUserId(), year);
        AnnualForm form = getAnnual(user, annualGoals.get(0));
        form.setAnnualGoal1(annualGoals.get(0).getAnnualGoal());
        form.setAnnualGoal2(annualGoals.get(1).getAnnualGoal());
        form.setAnnualGoal3(annualGoals.get(2).getAnnualGoal());

        model.addAttribute("form", form);

        return "annual/annualGoal";
    }

    
    public AnnualForm getAnnual(UserInf user, Annual entity) {
    	modelMapper.typeMap(Annual.class, AnnualForm.class).addMappings(mapper -> mapper.skip(AnnualForm::setUser_id));
    	AnnualForm form = modelMapper.map(entity, AnnualForm.class);
    	return form;
    }

    private Annual buildAnnualGoal(Long userId, AnnualForm form, String annualGoal) {
		Annual entity = new Annual();
        
        entity.setUserId(userId);
        entity.setYear(form.getYear());
        entity.setStatus(false);
        entity.setAnnualGoal(annualGoal);

        return entity;
    }
}
