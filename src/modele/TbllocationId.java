package modele;
// Generated 2015-11-15 12:00:10 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

/**
 * TbllocationId generated by hbm2java
 */
public class TbllocationId implements java.io.Serializable {

	private BigDecimal idlocation;
	private String courrielusag;
	private String noseriecopie;

	public TbllocationId() {
	}

	public TbllocationId(BigDecimal idlocation, String courrielusag, String noseriecopie) {
		this.idlocation = idlocation;
		this.courrielusag = courrielusag;
		this.noseriecopie = noseriecopie;
	}

	public BigDecimal getIdlocation() {
		return this.idlocation;
	}

	public void setIdlocation(BigDecimal idlocation) {
		this.idlocation = idlocation;
	}

	public String getCourrielusag() {
		return this.courrielusag;
	}

	public void setCourrielusag(String courrielusag) {
		this.courrielusag = courrielusag;
	}

	public String getNoseriecopie() {
		return this.noseriecopie;
	}

	public void setNoseriecopie(String noseriecopie) {
		this.noseriecopie = noseriecopie;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbllocationId))
			return false;
		TbllocationId castOther = (TbllocationId) other;

		return ((this.getIdlocation() == castOther.getIdlocation()) || (this.getIdlocation() != null
				&& castOther.getIdlocation() != null && this.getIdlocation().equals(castOther.getIdlocation())))
				&& ((this.getCourrielusag() == castOther.getCourrielusag())
						|| (this.getCourrielusag() != null && castOther.getCourrielusag() != null
								&& this.getCourrielusag().equals(castOther.getCourrielusag())))
				&& ((this.getNoseriecopie() == castOther.getNoseriecopie())
						|| (this.getNoseriecopie() != null && castOther.getNoseriecopie() != null
								&& this.getNoseriecopie().equals(castOther.getNoseriecopie())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getIdlocation() == null ? 0 : this.getIdlocation().hashCode());
		result = 37 * result + (getCourrielusag() == null ? 0 : this.getCourrielusag().hashCode());
		result = 37 * result + (getNoseriecopie() == null ? 0 : this.getNoseriecopie().hashCode());
		return result;
	}

}
