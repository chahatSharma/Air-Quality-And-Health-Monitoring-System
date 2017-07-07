package com.neu.business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;

import com.neu.pojo.Vitalsign;

/**
 *
 * @author chahatSharma
 */
public class CheckVitalSignNormality {

    private Vitalsign vs;
    private int patientAge;
    private String normal = "Normal";
    private String abnormal = "Abnormal";

    public String checkVitalSign(Vitalsign vs, int patientAge) {
        int age = patientAge;
        int respRate = vs.getRespirationRate();
        int heartRate = vs.getHeartRate();
        int bp = vs.getSysBp();
        float weight = vs.getWeight();
        if (age >= Constants.MIN_AGE_TODDLER && age <= Constants.MAX_AGE_TODDLER) {
            if ((respRate >= Constants.MIN_RESP_RATE_TODDLER && respRate <= Constants.MAX_RESP_RATE_TODDLER)
                    && (heartRate >= Constants.MIN_HEART_RATE_TODDLER && heartRate <= Constants.MAX_HEART_RATE_TODDLER)
                    && (bp >= Constants.MIN_BP_TODDLER && bp <= Constants.MAX_BP_TODDLER)
                    && (weight >= Constants.MIN_WEIGHT_TODDLER && weight <= Constants.MAX_WEIGHT_TODDLER)) {
                return normal;
            } else {
                return abnormal;

            }
        } else if (age >= Constants.MIN_AGE_PRESCHOOLER && age <= Constants.MAX_AGE_PRESCHOOLER) {
            if ((respRate >= Constants.MIN_RESP_RATE_PRESCHOOLER && respRate <= Constants.MAX_RESP_RATE_PRESCHOOLER)
                    && (heartRate >= Constants.MIN_RESP_RATE_PRESCHOOLER && heartRate <= Constants.MAX_RESP_RATE_PRESCHOOLER)
                    && (bp >= Constants.MIN_BP_PRESCHOOLER && bp <= Constants.MAX_BP_PRESCHOOLER)
                    && (weight >= Constants.MIN_WEIGHT_PRESCHOOLER && weight <= Constants.MAX_WEIGHT_PRESCHOOLER)) {
                return normal;
            } else {
                return abnormal;

            }
        } else if (age >= Constants.MIN_AGE_SCHOOLAGE && age <= Constants.MAX_AGE_SCHOOLAGE) {
            if ((respRate >= Constants.MIN_RESP_RATE_SCHOOLAGE && respRate <= Constants.MAX_RESP_RATE_SCHOOLAGE)
                    && (heartRate >= Constants.MIN_HEART_RATE_SCHOOLAGE && heartRate <= Constants.MAX_HEART_RATE_SCHOOLAGE)
                    && (bp >= Constants.MIN_BP_SCHOOLAGE && bp <= Constants.MAX_BP_SCHOOLAGE)
                    && (weight >= Constants.MIN_WEIGHT_SCHOOLAGE && weight <= Constants.MAX_WEIGHT_SCHOOLAGE)) {
                return normal;
            } else {
                return abnormal;

            }
        } else if (age >= Constants.MIN_AGE_ADOLESCENT) {
            if ((respRate >= Constants.MIN_RESP_RATE_ADOLESCENT && respRate <= Constants.MAX_RESP_RATE_ADOLESCENT)
                    && (heartRate >= Constants.MIN_HEART_RATE_ADOLESCENT && heartRate <= Constants.MAX_HEART_RATE_ADOLESCENT)
                    && (bp >= Constants.MIN_BP_ADOLESCENT && bp <= Constants.MAX_BP_ADOLESCENT)
                    && (weight > Constants.MIN_WEIGHT_ADOLESCENT)) {
                return normal;
            } else {
                return abnormal;

            }
        } else {
            return abnormal;
        }

    }
/*
    public HashMap<String, Integer> getAbnormalOccurenceCount(VitalSignHistory vsh, int patientAge) {
        //System.out.println("patient Age" + patientAge);
        int size = 0;
        if (null != vsh && null != vsh.getVitalSignHistory()) {
            size = vsh.getVitalSignHistory().size();
        }
        HashMap<String, Integer> map = new HashMap<>();
        int respRateAbnormalityCount = 0;
        int heartRateAbnormalityCount = 0;
        int bpAbnormalityCount = 0;
        int weightAbnormalityCount = 0;
       int age = patientAge;
        for (int i = 0; i < size; i++) {
            vs = vsh.getVitalSignHistory().get(i);
            
            int respRate = vs.getRespirationRate();
            int heartRate = vs.getHeartRate();
            int bp = vs.getSysBP();
            float weight = vs.getWeight();
             
            if (age >= Constants.MIN_AGE_TODDLER && age <= Constants.MAX_AGE_TODDLER) {
                if ((respRate >= Constants.MIN_RESP_RATE_TODDLER && respRate <= Constants.MAX_RESP_RATE_TODDLER)) {
                    
                }
                else
                    respRateAbnormalityCount = respRateAbnormalityCount + 1;
                if (heartRate >= Constants.MIN_HEART_RATE_TODDLER && heartRate <= Constants.MAX_HEART_RATE_TODDLER) {
                    
                }
                else
                    heartRateAbnormalityCount = heartRateAbnormalityCount + 1;
                if (bp >= Constants.MIN_BP_TODDLER && bp <= Constants.MAX_BP_TODDLER) {
                    
                }
                else
                    bpAbnormalityCount = bpAbnormalityCount + 1;
                if (weight >= Constants.MIN_WEIGHT_TODDLER && weight <= Constants.MAX_WEIGHT_TODDLER) {
                    
                }
                else
                    weightAbnormalityCount = weightAbnormalityCount + 1;

            } else if (age >= Constants.MIN_AGE_PRESCHOOLER && age <= Constants.MAX_AGE_PRESCHOOLER) {
                if (respRate >= Constants.MIN_RESP_RATE_PRESCHOOLER && respRate <= Constants.MAX_RESP_RATE_PRESCHOOLER) {
                    
                }
                else
                    respRateAbnormalityCount = respRateAbnormalityCount + 1;
                if (heartRate >= Constants.MIN_RESP_RATE_PRESCHOOLER && heartRate <= Constants.MAX_RESP_RATE_PRESCHOOLER) {
                    
                }
                else
                    heartRateAbnormalityCount = heartRateAbnormalityCount + 1;
                if (bp >= Constants.MIN_BP_PRESCHOOLER && bp <= Constants.MAX_BP_PRESCHOOLER) {
                    
                }
                bpAbnormalityCount = bpAbnormalityCount + 1;
                if (weight >= Constants.MIN_WEIGHT_PRESCHOOLER && weight <= Constants.MAX_WEIGHT_PRESCHOOLER) {
                    
                }
                else
                    weightAbnormalityCount = weightAbnormalityCount + 1;
            } else if (age >= Constants.MIN_AGE_SCHOOLAGE && age <= Constants.MAX_AGE_SCHOOLAGE) {
                if (respRate >= Constants.MIN_RESP_RATE_SCHOOLAGE && respRate <= Constants.MAX_RESP_RATE_SCHOOLAGE) {
                    
                }
                else
                    respRateAbnormalityCount = respRateAbnormalityCount + 1;
                if (heartRate >= Constants.MIN_HEART_RATE_SCHOOLAGE && heartRate <= Constants.MAX_HEART_RATE_SCHOOLAGE) {
                    
                }
                else
                    heartRateAbnormalityCount = heartRateAbnormalityCount + 1;
                if (bp >= Constants.MIN_BP_SCHOOLAGE && bp <= Constants.MAX_BP_SCHOOLAGE) {
                    
                }
                    else
                    bpAbnormalityCount = bpAbnormalityCount + 1;
                if (weight >= Constants.MIN_WEIGHT_SCHOOLAGE && weight <= Constants.MAX_WEIGHT_SCHOOLAGE) {
                    
                }
                else
                    weightAbnormalityCount = weightAbnormalityCount + 1;
            } else if (age >= Constants.MIN_AGE_ADOLESCENT) {
                if (respRate >= Constants.MIN_RESP_RATE_ADOLESCENT && respRate <= Constants.MAX_RESP_RATE_ADOLESCENT) {
                    
                }
                else
                    respRateAbnormalityCount = respRateAbnormalityCount + 1;
                if (heartRate >= Constants.MIN_HEART_RATE_ADOLESCENT && heartRate <= Constants.MAX_HEART_RATE_ADOLESCENT) {
                    
                }
                else
                    heartRateAbnormalityCount = heartRateAbnormalityCount + 1;
                if (bp >= Constants.MIN_BP_ADOLESCENT && bp <= Constants.MAX_BP_ADOLESCENT) {
                    
                }
                else
                    bpAbnormalityCount = bpAbnormalityCount + 1;
                if (weight > Constants.MIN_WEIGHT_ADOLESCENT) {
                    
                }
                else
                    weightAbnormalityCount = weightAbnormalityCount + 1;

            }
        }
        map.put(Constants.RESP_RATE_ABNORMAL, respRateAbnormalityCount);
        map.put(Constants.HEART_RATE_ABNORMAL, heartRateAbnormalityCount);
        map.put(Constants.BP_ABNORMAL, bpAbnormalityCount);
        map.put(Constants.WEIGHT_ABNORMAL, weightAbnormalityCount);
        
        return map;
    }
*/
}

