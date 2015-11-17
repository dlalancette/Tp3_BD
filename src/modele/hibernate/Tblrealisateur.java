package modele.hibernate;
// Generated 2015-11-15 12:00:10 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tblrealisateur generated by hbm2java
 */
public class Tblrealisateur implements java.io.Serializable {

	private BigDecimal idreal;
	private String nomreal;
	private String prenreal;
	private Date datenaissreal;
	private String lieunaissreal;
	private String bioreal;
	private Set tblfilms = new HashSet(0);

	public Tblrealisateur() {
	}

	public Tblrealisateur(BigDecimal idreal, String nomreal, String prenreal) {
		this.idreal = idreal;
		this.nomreal = nomreal;
		this.prenreal = prenreal;
	}

	public Tblrealisateur(BigDecimal idreal, String nomreal, String prenreal, Date datenaissreal, String lieunaissreal,
			String bioreal, Set tblfilms) {
		this.idreal = idreal;
		this.nomreal = nomreal;
		this.prenreal = prenreal;
		this.datenaissreal = datenaissreal;
		this.lieunaissreal = lieunaissreal;
		this.bioreal = bioreal;
		this.tblfilms = tblfilms;
	}

	public BigDecimal getIdreal() {
		return this.idreal;
	}

	public void setIdreal(BigDecimal idreal) {
		this.idreal = idreal;
	}

	public String getNomreal() {
		return this.nomreal;
	}

	public void setNomreal(String nomreal) {
		this.nomreal = nomreal;
	}

	public String getPrenreal() {
		return this.prenreal;
	}

	public void setPrenreal(String prenreal) {
		this.prenreal = prenreal;
	}

	public Date getDatenaissreal() {
		return this.datenaissreal;
	}

	public void setDatenaissreal(Date datenaissreal) {
		this.datenaissreal = datenaissreal;
	}

	public String getLieunaissreal() {
		return this.lieunaissreal;
	}

	public void setLieunaissreal(String lieunaissreal) {
		this.lieunaissreal = lieunaissreal;
	}

	public String getBioreal() {
		return this.bioreal;
	}

	public void setBioreal(String bioreal) {
		this.bioreal = bioreal;
	}

	public Set getTblfilms() {
		return this.tblfilms;
	}

	public void setTblfilms(Set tblfilms) {
		this.tblfilms = tblfilms;
	}

}
