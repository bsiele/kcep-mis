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
public class InputsCollectionDetails implements Serializable, Comparable<InputsCollectionDetails> {

    public InputsCollectionDetails() {
    }

    public InputsCollectionDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public InputTypeDetails getInputType() {
        return inputType;
    }

    public void setInputType(InputTypeDetails inputType) {
        this.inputType = inputType;
    }

    public PersonDetails getAgroDealer() {
        return agroDealer;
    }

    public void setAgroDealer(PersonDetails agroDealer) {
        this.agroDealer = agroDealer;
    }

    public PersonDetails getFarmer() {
        return farmer;
    }

    public void setFarmer(PersonDetails farmer) {
        this.farmer = farmer;
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
        if (!(object instanceof InputsCollectionDetails)) {
            return false;
        }
        InputsCollectionDetails other = (InputsCollectionDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.entities.InputsCollection[ id=" + id + " ]";
    }

    @Override
    public int compareTo(InputsCollectionDetails o) {
        return this.id.compareTo(o.getId());
    }

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date date;
    private String quantity;
    private InputTypeDetails inputType;
    private PersonDetails agroDealer;
    private PersonDetails farmer;

}
