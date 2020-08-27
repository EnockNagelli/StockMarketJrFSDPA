package com.iiht.stockExchange.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CompanyDetails")
public class CompanyDetails implements Serializable {

	private static final long serialVersionUID = 7203557872090360571L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "companyCode")
	private Long companyCode;
	
	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "stockExchange")
	private String stockExchange;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "companyName")
	private String companyName;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "companyCEO")
	private String companyCEO;

	@NotNull
	@Column(name = "turnover", precision=10, scale=2)
	private Double turnover;

	@NotNull
	@Size(min = 1, max = 500)
	@Column(name = "boardOfDirectors")
	private String boardOfDirectors;

	@NotNull
	@Size(min = 1, max = 1000)
	@Column(name = "companyProfile")
	private String companyProfile;								// Brief writeup, about companies Services/Product, etc…
	
	@OneToMany(cascade= {CascadeType.MERGE})
	@JoinColumn(name="companyCode")
	private Set<StockPriceDetails> StockPriceDetails;
}

