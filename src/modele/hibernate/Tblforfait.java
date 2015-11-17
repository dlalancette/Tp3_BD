package modele.hibernate;
// Generated 2015-11-15 12:00:10 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Tblforfait generated by hbm2java
 */
public class Tblforfait implements java.io.Serializable {

	private String nomforfait;
	private BigDecimal coutforfait;
	private BigDecimal nblocmax;
	private String dureemax;
	private Set tblusagers = new HashSet(0);

	public Tblforfait() {
	}

	public Tblforfait(String nomforfait, BigDecimal coutforfait, BigDecimal nblocmax, String dureemax) {
		this.nomforfait = nomforfait;
		this.coutforfait = coutforfait;
		this.nblocmax = nblocmax;
		this.dureemax = dureemax;
	}

	public Tblforfait(String nomforfait, BigDecimal coutforfait, BigDecimal nblocmax, String dureemax, Set tblusagers) {
		this.nomforfait = nomforfait;
		this.coutforfait = coutforfait;
		this.nblocmax = nblocmax;
		this.dureemax = dureemax;
		this.tblusagers = tblusagers;
	}

	public String getNomforfait() {
		return this.nomforfait;
	}

	public void setNomforfait(String nomforfait) {
		this.nomforfait = nomforfait;
	}

	public BigDecimal getCoutforfait() {
		return this.coutforfait;
	}

	public void setCoutforfait(BigDecimal coutforfait) {
		this.coutforfait = coutforfait;
	}

	public BigDecimal getNblocmax() {
		return this.nblocmax;
	}

	public void setNblocmax(BigDecimal nblocmax) {
		this.nblocmax = nblocmax;
	}

	public String getDureemax() {
		return this.dureemax;
	}

	public void setDureemax(String dureemax) {
		this.dureemax = dureemax;
	}

	public Set getTblusagers() {
		return this.tblusagers;
	}

	public void setTblusagers(Set tblusagers) {
		this.tblusagers = tblusagers;
	}

}
