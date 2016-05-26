/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author siech
 */
public class EVoucherDetails implements Serializable, Comparable<EVoucherDetails> {

    public EVoucherDetails() {
    }

    public EVoucherDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDateRedeemed() {
        return dateRedeemed;
    }

    public void setDateRedeemed(Date dateRedeemed) {
        this.dateRedeemed = dateRedeemed;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public InputTypeDetails getInputType() {
        return inputType;
    }

    public void setInputType(InputTypeDetails inputType) {
        this.inputType = inputType;
    }

    public PersonDetails getPerson() {
        return person;
    }

    public void setPerson(PersonDetails person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EVoucherDetails)) {
            return false;
        }
        EVoucherDetails other = (EVoucherDetails) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.EVoucher[ id=" + id + " ]";
    }

    @Override
    public int compareTo(EVoucherDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;
    private String amount;
    private Date dateRedeemed;
    private String photo;
    private InputTypeDetails inputType;
    private PersonDetails person;

}
