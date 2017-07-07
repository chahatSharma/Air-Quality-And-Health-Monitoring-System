package com.neu.business;

import org.codehaus.jackson.annotate.JsonIgnore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chahatSharma
 */
public class GoogleResponse {
 
 
 private Result[] results ;
 private String status ;

 public Result[] getResults() {
  return results;
 }
 public void setResults(Result[] results) {
  this.results = results;
 }
 public String getStatus() {
  return status;
 }
 public void setStatus(String status) {
  this.status = status;
 }
 
 

}
