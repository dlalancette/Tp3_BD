package modele.hibernate;
// Generated 2015-11-15 12:00:10 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Tbllocation generated by hbm2java
 */
public class Tbllocation implements java.io.Serializable {

	private TbllocationId id;
	private Tblcopie tblcopie;
	private Tblusager tblusager;
	private Date dateloc;
	private String commentaireloc;

	public Tbllocation() {
	}

	public Tbllocation(TbllocationId id, Tblcopie tblcopie, Tblusager tblusager, Date dateloc) {
		this.id = id;
		this.tblcopie = tblcopie;
		this.tblusager = tblusager;
		this.dateloc = dateloc;
	}

	public Tbllocation(TbllocationId id, Tblcopie tblcopie, Tblusager tblusager, Date dateloc, String commentaireloc) {
		this.id = id;
		this.tblcopie = tblcopie;
		this.tblusager = tblusager;
		this.dateloc = dateloc;
		this.commentaireloc = commentaireloc;
	}

	public TbllocationId getId() {
		return this.id;
	}

	public void setId(TbllocationId id) {
		this.id = id;
	}

	public Tblcopie getTblcopie() {
		return this.tblcopie;
	}

	public void setTblcopie(Tblcopie tblcopie) {
		this.tblcopie = tblcopie;
	}

	public Tblusager getTblusager() {
		return this.tblusager;
	}

	public void setTblusager(Tblusager tblusager) {
		this.tblusager = tblusager;
	}

	public Date getDateloc() {
		return this.dateloc;
	}

	public void setDateloc(Date dateloc) {
		this.dateloc = dateloc;
	}

	public String getCommentaireloc() {
		return this.commentaireloc;
	}

	public void setCommentaireloc(String commentaireloc) {
		this.commentaireloc = commentaireloc;
	}

}
