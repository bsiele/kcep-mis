/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.component.sub;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Component;
import ke.co.miles.kcep.mis.entities.SubComponent;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.utilities.SubComponentDetails;

/**
 *
 * @author siech
 */
@Stateless
public class SubComponentRequests extends EntityRequests implements SubComponentRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addSubComponent(SubComponentDetails subComponentDetails) throws MilesException {

        if (subComponentDetails == null) {
            throw new InvalidArgumentException("error_023_01");
        } else if (subComponentDetails.getSubComponent() == null) {
            throw new InvalidArgumentException("error_023_02");
        } else if (subComponentDetails.getSubComponent().length() > 200) {
            throw new InvalidArgumentException("error_023_03");
        } else if (subComponentDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_023_04");
        }

        SubComponent subComponent;
        setQ(getEm().createNamedQuery("SubComponent.findBySubComponentAndComponentId"));
        getQ().setParameter("subComponent", subComponentDetails.getSubComponent());
        getQ().setParameter("componentId", subComponentDetails.getComponent().getId());
        try {
            subComponent = (SubComponent) getQ().getSingleResult();
        } catch (Exception e) {
            subComponent = null;
        }
        if (subComponent != null) {
            throw new InvalidArgumentException("error_023_05");
        }

        subComponent = new SubComponent();
        subComponent.setSubComponent(subComponentDetails.getSubComponent());
        subComponent.setComponent(getEm().getReference(Component.class, subComponentDetails.getComponent().getId()));

        try {
            getEm().persist(subComponent);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return subComponent.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<SubComponentDetails> retrieveSubComponents() throws MilesException {
        List<SubComponent> subComponents = new ArrayList<>();
        setQ(getEm().createNamedQuery("SubComponent.findAll"));
        try {
            subComponents = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertSubComponentsToSubComponentDetailsList(subComponents);
    }

    @Override
    public SubComponentDetails retrieveSubComponent(int id) throws MilesException {
        SubComponent subComponent;
        setQ(getEm().createNamedQuery("SubComponent.findById"));
        getQ().setParameter("id", id);
        try {
            subComponent = (SubComponent) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubComponentToSubComponentDetails(subComponent);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editSubComponent(SubComponentDetails subComponentDetails) throws MilesException {

        if (subComponentDetails == null) {
            throw new InvalidArgumentException("error_023_01");
        } else if (subComponentDetails.getId() == null) {
            throw new InvalidArgumentException("error_023_06");
        } else if (subComponentDetails.getSubComponent() == null) {
            throw new InvalidArgumentException("error_023_02");
        } else if (subComponentDetails.getSubComponent().length() > 200) {
            throw new InvalidArgumentException("error_023_03");
        } else if (subComponentDetails.getComponent() == null) {
            throw new InvalidArgumentException("error_023_04");
        }

        SubComponent subComponent;
        setQ(getEm().createNamedQuery("SubComponent.findBySubComponentAndComponentId"));
        getQ().setParameter("subComponent", subComponentDetails.getSubComponent());
        getQ().setParameter("componentId", subComponentDetails.getComponent().getId());
        try {
            subComponent = (SubComponent) getQ().getSingleResult();
        } catch (Exception e) {
            subComponent = null;
        }
        if (subComponent != null) {
            if (subComponent.getId().equals(subComponentDetails.getId())) {
                throw new InvalidArgumentException("error_023_05");
            }
        }

        subComponent = getEm().find(SubComponent.class, subComponentDetails.getId());
        subComponent.setId(subComponentDetails.getId());
        subComponent.setSubComponent(subComponentDetails.getSubComponent());
        subComponent.setComponent(getEm().getReference(Component.class, subComponentDetails.getComponent().getId()));

        try {
            getEm().merge(subComponent);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeSubComponent(int id) throws MilesException {
        SubComponent subComponent = getEm().find(SubComponent.class, id);
        try {
            getEm().remove(subComponent);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public SubComponentDetails convertSubComponentToSubComponentDetails(SubComponent subComponent) {

        SubComponentDetails subComponentDetails = new SubComponentDetails();
        try {
            subComponentDetails.setId(subComponent.getId());
        } catch (Exception e) {
        }
        try {
            subComponentDetails.setSubComponent(subComponent.getSubComponent());
        } catch (Exception e) {
        }
        try {
            subComponentDetails.setComponent(componentService.convertComponentToComponentDetails(subComponent.getComponent()));
        } catch (Exception e) {
        }
        return subComponentDetails;

    }

    private List<SubComponentDetails> convertSubComponentsToSubComponentDetailsList(List<SubComponent> subComponents) {

        List<SubComponentDetails> subComponentDetailsList = new ArrayList<>();
        for (SubComponent subComponent : subComponents) {
            subComponentDetailsList.add(convertSubComponentToSubComponentDetails(subComponent));
        }

        return subComponentDetailsList;

    }

//</editor-fold>
    @EJB
    private ComponentRequestsLocal componentService;
}