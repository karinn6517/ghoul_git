package karin.slot.ghoul.dto;

import lombok.Data;

@Data
public class GhoulForm {
  private int count1;
  private int total1;
  private int count2;
  private int total2;
  private int count3;
  private int total3;
  private int count4;
  private int total4;
  private int kk;
  private boolean toggleFlag;
}

// integer から int に変更したのは、null許容にする必要がないため。