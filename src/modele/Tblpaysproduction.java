package modele;
// Generated 2015-11-15 12:00:10 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Tblpaysproduction generated by hbm2java
 */
public class Tblpaysproduction implements java.io.Serializable {

	private String nompays;
	private Set tblfilms = new HashSet(0);

	public Tblpaysproduction() {
	}

	public Tblpaysproduction(String nompays) {
		this.nompays = nompays;
	}

	public Tblpaysproduction(String nompays, Set tblfilms) {
		this.nompays = nompays;
		this.tblfilms = tblfilms;
	}

	public String getNompays() {
		return this.nompays;
	}

	public void setNompays(String nompays) {
		this.nompays = nompays;
	}

	public Set getTblfilms() {
		return this.tblfilms;
	}

	public void setTblfilms(Set tblfilms) {
		this.tblfilms = tblfilms;
	}

}