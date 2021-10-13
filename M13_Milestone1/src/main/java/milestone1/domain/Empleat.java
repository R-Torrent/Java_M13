package milestone1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Empleat {
	
	private @Id @GeneratedValue Long id;
	private String nom;
	private String cognom;
	private Feines feina;
	
	public enum Feines {
		
		CAPITA(70000),
		OFICIAL(50000),
		MARINER(20000),
		POLISSO(0);
		
		private int salari;
		
		Feines(int salari) {
			this.salari = salari;
		}
		
		public int getSalari() {
			return salari;
		}
		
	}
	
	public Empleat() {}
	
	public Empleat(String nom, String cognom, Feines feina) {
		this.nom = nom;
		this.cognom = cognom;
		this.feina = feina;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getCognom() {
		return cognom;
	}
	
	public Feines getFeina() {
		return feina;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setCognom(String cognom) {
		this.cognom = cognom;
	}
	
	public void setFeina(Feines feina) {
		this.feina = feina;
	}
	
	@Override
	public String toString() {
		return "Empleat{ id=\'" + id + "\', nom=\'" + nom + "\', cognom=\'" + cognom
				+ "\', feina=\'" + feina + "\' }";
	}
	
}
