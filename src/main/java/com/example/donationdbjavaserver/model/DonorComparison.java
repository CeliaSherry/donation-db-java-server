package com.example.donationdbjavaserver.model;

import java.util.Comparator;

public class DonorComparison implements Comparator<Donor> {
  @Override
  public int compare(Donor o1, Donor o2) {
    return o1.getDonorName().compareTo(o2.getDonorName());
  }
}
