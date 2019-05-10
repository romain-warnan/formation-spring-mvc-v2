package fr.insee.springmvc.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Commande {

	static AtomicLong counter = new AtomicLong(0L);
	
	private long id;
	private double prix;
	
	private Commande() {}
	
	public static Commande createFrom(List<Cocktail> cocktails) {
		Commande commande = new Commande();
		commande.id = counter.incrementAndGet();
		commande.prix = cocktails.stream().mapToDouble(Cocktail::getPrix).sum();
		return commande;
	}

	public long getId() {
		return id;
	}

	public double getPrix() {
		return prix;
	}
}
