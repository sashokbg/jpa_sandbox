package com.example.onetomany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
class CompanyDetails {
  @Id
  String details;

  @ManyToOne
  Company company;

  public CompanyDetails() {
  }

  public CompanyDetails(Company companyId, String detail) {
    this.company = companyId;
    this.details = detail;
  }
}
