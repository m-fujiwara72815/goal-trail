package com.example.goaltrail.controller;

import java.security.Principal;
import java.time.Year;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.goaltrail.entity.UserInf;
import com.example.goaltrail.repository.AnnualRepository;

@Controller
public class GoalController {

	@Autowired
	private AnnualRepository repository;

	@GetMapping("/checkRegisration")
	public String newTopic(Principal principal) {
		Authentication authentication = (Authentication) principal;
		UserInf user = (UserInf) authentication.getPrincipal();

		int thisYear = Year.now().getValue();
		// 年間目標を登録済みか確認
		boolean hasAnnualGoal = repository.existsByUserIdAndYear(user.getUserId(), thisYear);
		if (!hasAnnualGoal) {
			return "redirect:/annual/annualRegister";
		}

		//登録がある場合は年目標画面を表示
		return "redirect:/annual/" + thisYear;		
	}
}