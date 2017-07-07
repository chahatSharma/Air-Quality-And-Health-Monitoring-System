package com.neu.business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Date;

/**
 *
 * @author chahatSharma
 */
public class Constants {
	
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	public static final String ID_PATTERN = "[0-9]+";  
	public static final String STRING_PATTERN = "[a-zA-Z]+";  
	public static final String MOBILE_PATTERN = "[0-9]{10}";
	public static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
	public static final String ZIP_CODE_PATTERN="^[0-9]{5}(?:-[0-9]{4})?$";
	public static final String ALPHANUMERIC_PATTERN = "^[a-zA-Z0-9]*$";
    
    public static final int MIN_AGE_TODDLER = 1;
    public static final int MAX_AGE_TODDLER = 3;
    public static final int MIN_AGE_PRESCHOOLER = 4;
    public static final int MAX_AGE_PRESCHOOLER = 5;
    public static final int MIN_AGE_SCHOOLAGE= 6;
    public static final int MAX_AGE_SCHOOLAGE = 12;
    public static final int MIN_AGE_ADOLESCENT = 13;
    
    public static final int MIN_RESP_RATE_TODDLER = 20;
    public static final int MAX_RESP_RATE_TODDLER = 30;
    public static final int MIN_RESP_RATE_PRESCHOOLER = 20;
    public static final int MAX_RESP_RATE_PRESCHOOLER = 30;
    public static final int MIN_RESP_RATE_SCHOOLAGE = 20;
    public static final int MAX_RESP_RATE_SCHOOLAGE = 30;
    public static final int MIN_RESP_RATE_ADOLESCENT = 12;
    public static final int MAX_RESP_RATE_ADOLESCENT = 20;
    
    public static final int MIN_HEART_RATE_TODDLER = 80;
    public static final int MAX_HEART_RATE_TODDLER = 130;
    public static final int MIN_HEART_RATE_PRESCHOOLER = 80;
    public static final int MAX_HEART_RATE_PRESCHOOLER = 120;
    public static final int MIN_HEART_RATE_SCHOOLAGE = 70;
    public static final int MAX_HEART_RATE_SCHOOLAGE = 110;
    public static final int MIN_HEART_RATE_ADOLESCENT = 55;
    public static final int MAX_HEART_RATE_ADOLESCENT = 105;
    
    public static final int MIN_BP_TODDLER = 80;
    public static final int MAX_BP_TODDLER = 110;
    public static final int MIN_BP_PRESCHOOLER = 80;
    public static final int MAX_BP_PRESCHOOLER = 110;
    public static final int MIN_BP_SCHOOLAGE = 80;
    public static final int MAX_BP_SCHOOLAGE = 120;
    public static final int MIN_BP_ADOLESCENT = 110;
    public static final int MAX_BP_ADOLESCENT = 120;
    
    
    public static final int MIN_WEIGHT_TODDLER = 22;
    public static final int MAX_WEIGHT_TODDLER = 31;
    public static final int MIN_WEIGHT_PRESCHOOLER = 31;
    public static final int MAX_WEIGHT_PRESCHOOLER = 40;
    public static final int MIN_WEIGHT_SCHOOLAGE = 41;
    public static final int MAX_WEIGHT_SCHOOLAGE = 92;
    public static final int MIN_WEIGHT_ADOLESCENT = 110;
    
    public static final String RESP_RATE_ABNORMAL="Abnormal Respiration Rate";
    public static final String HEART_RATE_ABNORMAL="Abnormal Heart Rate";
    public static final String BP_ABNORMAL="Abnormal Blood Pressure Rate";
    public static final String WEIGHT_ABNORMAL="Abnormal Weight Rate";
    
    public static final String RESP_RATE_NORMAL="Normal RespirationRate";
    public static final String HEART_RATE_NORMAL="Normal Heart Rate";
    public static final String BP_NORMAL="Normal Blood Pressure Rate";
    public static final String WEIGHT_NORMAL="Normal Weight Rate";
    
    
          
}

