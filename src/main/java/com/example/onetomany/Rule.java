package com.example.onetomany;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrimaryKeyJoinColumn;
import java.util.List;

@Embeddable
class Rule {
  String name;

//  @ElementCollection
//  List<RulePolicy> rulePolicies;

  public Rule() {
  }

  public Rule(String name, List<RulePolicy> rulePolicies) {
    this.name = name;
//    this.rulePolicies = rulePolicies;
  }
}
