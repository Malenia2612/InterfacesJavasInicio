package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.RentCar;
import model.entities.Vehicle;
import model.service.BrasilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel: ");
		System.out.print("Modelo do carro: ");
		String mvehicle = sc.nextLine();
		
		System.out.println("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.println("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
		
		RentCar rc = new RentCar(start, finish, new Vehicle(mvehicle));
		
		System.out.print("Entre com o preço por hora: ");
		double priceH = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double priceD = sc.nextDouble();
		
		RentalService rentalService = new RentalService(priceH, priceD, new BrasilTaxService());
		
		
		rentalService.processInvoice(rc);
		
		System.out.println("FATURA");
		System.out.println("Pagamento Básico: " + String.format("%.2f", rc.getInvoice().getBasicPayment()));
		
		System.out.println("Imposto: " + String.format("%.2f", rc.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", rc.getInvoice().getTotalPayment()));
		
		
		sc.close();

	}

}
