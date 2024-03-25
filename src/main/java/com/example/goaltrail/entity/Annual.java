package com.example.goaltrail.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "annual")
@Data
@EqualsAndHashCode(callSuper = false)
public class Annual extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	

    @Id
    @SequenceGenerator(name = "annual_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long annualId;

    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private int year;

    @Column(nullable = false, length = 1000)
    private String annualGoal;
    
    @Column(nullable = false)
    private Boolean status;
}
