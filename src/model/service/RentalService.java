package model.service;

import java.time.Duration;

import model.entities.Invoice;
import model.entities.RentCar;

public class RentalService {
	private Double priceH;
	private Double priceD;
	
	private BrasilTaxService taxService;
	
	
	public RentalService(Double priceH, Double priceD, BrasilTaxService taxService) {
		this.priceH = priceH;
		this.priceD = priceD;
		this.taxService = taxService;
	}

	public void processInvoice (RentCar rentCar) {
		
		double minutes = Duration.between(rentCar.getStart(), rentCar.getFinish()).toMinutes();
		double hours = minutes / 60.0;
		
		double basicPayments;
		if (hours <= 12.0) {
			basicPayments = priceH * Math.ceil(hours);
		}
		else {
			basicPayments = priceD * Math.ceil(hours / 24.0);
		}
		double tax = taxService.tax(basicPayments);
		
		rentCar.setInvoice(new Invoice(basicPayments, tax));
	}
	
}
