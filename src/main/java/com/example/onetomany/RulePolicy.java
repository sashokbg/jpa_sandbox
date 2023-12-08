package com.example.onetomany;

import jakarta.persistence.Embeddable;

@Embeddable
class RulePolicy {
  int minAmount;
  int maxAmount;

  public RulePolicy() {
  }

  public RulePolicy(int minAmount, int maxAmount) {
    this.minAmount = minAmount;
    this.maxAmount = maxAmount;
  }
}
