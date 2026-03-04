package model.services;

public class PaypalService implements OnlinePaymentService {
	
	private static final double tax = 0.02;
	private static final double jurosMensal = 0.01;
	
	@Override
	public Double paymentFee(double amount) {
		return amount * tax;
	}

	@Override
	public Double interest(double amount, int months) {
		return amount * jurosMensal * months;
	}
	
}
