package com.example.goaltrail.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AnnualForm {
	
	private Long annual_id;
	private Long user_id;

	@Min(value = 1800)
	@Max(value = 2300)
    private int year;

    @NotEmpty
    @Size(max = 100)
    private String annualGoal1;

    @NotEmpty
    @Size(max = 100)
    private String annualGoal2;

    @NotEmpty
    @Size(max = 100)
    private String annualGoal3;

    public String[] getAnnualGoals() {
    	String[] goals = {
    		annualGoal1,
    		annualGoal2,
    		annualGoal3,
    	};
    	return goals;
    }
}

