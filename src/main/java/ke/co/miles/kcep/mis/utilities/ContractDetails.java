/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

import java.io.Serializable;

/**
 *
 * @author siech
 */
public class ContractDetails implements Serializable, Comparable<ContractDetails> {

    private static final long serialVersionUID = 1L;

    public ContractDetails() {
    }

    public ContractDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContractDetails)) {
            return false;
        }
        ContractDetails other = (ContractDetails) object;
        return !((this.id == null && other.getId() != null) || (this.id != null && !this.id.equals(other.getId())));
    }

    @Override
    public String toString() {
        return "ke.co.miles.kcep.mis.utilities.ContractDetails[ id=" + id + " ]";
    }

    @Override
    public int compareTo(ContractDetails o) {
        return this.id.compareTo(o.getId());
    }

    private Integer id;

}
