package com.iiht.stockExchange.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "StockPriceDetails")
public class StockPriceDetails implements Serializable {

	private static final long serialVersionUID = 1558661735734686934L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "stockIndex")
	private Long stockIndex;
	
	@NotNull
	@Column(name = "companyCode")
	private Long companyCode;									// To which Company this Stock Price Info belongs to

	@NotNull
	@Column(name = "currentStockPrice", precision=10, scale=2)
	private Double currentStockPrice;							// Stock Price
	
	@NotNull
	@Column(name = "stockPriceDate")
	private LocalDate stockPriceDate;							// Date of the Stock Price
	
	@NotNull
	@Column(name = "stockPriceTime", columnDefinition = "TIME")
	private LocalTime stockPriceTime;								// Stock Price at this Specific
}