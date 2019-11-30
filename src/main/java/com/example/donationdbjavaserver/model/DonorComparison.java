package com.example.donationdbjavaserver.model;

import java.util.Comparator;

public class DonorComparison implements Comparator<Donor> {
  boolean ascending = true;
  public DonorComparison(boolean ascending){
    this.ascending = ascending;
  }
  @Override
  public int compare(Donor o1, Donor o2) {
    String lowerCaseO2 = o2.getDonorName().toLowerCase();
    String lowerCaseO1 = o1.getDonorName().toLowerCase();
    //System.out.println("comparing 2 donor objects, "+ lowerCaseO1+" and "+ lowerCaseO2 + "result : "+lowerCaseO1.compareTo(lowerCaseO2));
   // return o1.getDonorName().compareTo(o2.getDonorName());
    int result = lowerCaseO1.compareTo(lowerCaseO2);
    return this.ascending? result: -result;
  }
}