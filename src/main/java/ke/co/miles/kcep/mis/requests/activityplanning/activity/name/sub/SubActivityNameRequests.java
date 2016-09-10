/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.name.sub;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityName;
import ke.co.miles.kcep.mis.entities.SubActivityName;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.ActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.utilities.SubActivityNameDetails;

/**
 *
 * @author siech
 */
@Stateless
public class SubActivityNameRequests extends EntityRequests implements SubActivityNameRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addSubActivityName(SubActivityNameDetails subActivityNameDetails) throws MilesException {

        if (subActivityNameDetails == null) {
            throw new InvalidArgumentException("error_048_01");
        } else if (subActivityNameDetails.getName() == null) {
            throw new InvalidArgumentException("error_048_02");
        } else if (subActivityNameDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_048_03");
        } else if (subActivityNameDetails.getActivityName() == null) {
            throw new InvalidArgumentException("error_048_04");
        }

        SubActivityName subActivityName;
        setQ(getEm().createNamedQuery("SubActivityName.findByName"));
        getQ().setParameter("name", subActivityNameDetails.getName());
        try {
            subActivityName = (SubActivityName) getQ().getSingleResult();
        } catch (Exception e) {
            subActivityName = null;
        }
        if (subActivityName != null) {
            throw new InvalidArgumentException("error_048_05");
        }

        subActivityName = new SubActivityName();
        subActivityName.setName(subActivityNameDetails.getName());
        subActivityName.setActivityName(getEm().getReference(ActivityName.class, subActivityNameDetails.getActivityName().getId()));

        try {
            getEm().persist(subActivityName);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return subActivityName.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityNameDetails> retrieveSubActivityNames() throws MilesException {
        List<SubActivityName> activities = new ArrayList<>();
        setQ(getEm().createNamedQuery("SubActivityName.findAll"));
        try {
            activities = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertActivitiesToSubActivityNameDetailsList(activities);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SubActivityNameDetails> retrieveSubActivityNames(short activityNameId) throws MilesException {
        List<SubActivityName> activities = new ArrayList<>();
        setQ(getEm().createNamedQuery("SubActivityName.findByActivityNameId"));
        getQ().setParameter("activityNameId", activityNameId);
        try {
            activities = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertActivitiesToSubActivityNameDetailsList(activities);
    }

    @Override
    public SubActivityNameDetails retrieveSubActivityName(int id) throws MilesException {
        SubActivityName subActivityName;
        setQ(getEm().createNamedQuery("SubActivityName.findById"));
        getQ().setParameter("id", id);
        try {
            subActivityName = (SubActivityName) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubActivityNameToSubActivityNameDetails(subActivityName);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editSubActivityName(SubActivityNameDetails subActivityNameDetails) throws MilesException {

        if (subActivityNameDetails == null) {
            throw new InvalidArgumentException("error_048_01");
        } else if (subActivityNameDetails.getId() == null) {
            throw new InvalidArgumentException("error_048_06");
        } else if (subActivityNameDetails.getName() == null) {
            throw new InvalidArgumentException("error_048_02");
        } else if (subActivityNameDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_048_03");
        } else if (subActivityNameDetails.getActivityName() == null) {
            throw new InvalidArgumentException("error_048_04");
        }

        SubActivityName subActivityName;
        setQ(getEm().createNamedQuery("SubActivityName.findByName"));
        getQ().setParameter("name", subActivityNameDetails.getName());
        try {
            subActivityName = (SubActivityName) getQ().getSingleResult();
        } catch (Exception e) {
            subActivityName = null;
        }
        if (subActivityName != null) {
            if (subActivityName.getId().equals(subActivityNameDetails.getId())) {
                throw new InvalidArgumentException("error_048_05");
            }
        }

        subActivityName = getEm().find(SubActivityName.class, subActivityNameDetails.getId());
        subActivityName.setId(subActivityNameDetails.getId());
        subActivityName.setName(subActivityNameDetails.getName());
        subActivityName.setActivityName(getEm().getReference(ActivityName.class, subActivityNameDetails.getActivityName().getId()));

        try {
            getEm().merge(subActivityName);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeSubActivityName(int id) throws MilesException {
        SubActivityName subActivityName = getEm().find(SubActivityName.class, id);
        try {
            getEm().remove(subActivityName);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public SubActivityNameDetails convertSubActivityNameToSubActivityNameDetails(SubActivityName subActivityName) {

        if (subActivityName != null) {
            SubActivityNameDetails subActivityNameDetails = new SubActivityNameDetails(subActivityName.getId());
            subActivityNameDetails.setName(subActivityName.getName());
            subActivityNameDetails.setActivityName(activityNameService.
                    convertActivityNameToActivityNameDetails(subActivityName.getActivityName()));
            return subActivityNameDetails;
        } else {
            return null;
        }

    }

    private List<SubActivityNameDetails> convertActivitiesToSubActivityNameDetailsList(List<SubActivityName> activities) {

        List<SubActivityNameDetails> subActivityNameDetailsList = new ArrayList<>();
        for (SubActivityName subActivityName : activities) {
            subActivityNameDetailsList.add(convertSubActivityNameToSubActivityNameDetails(subActivityName));
        }

        return subActivityNameDetailsList;

    }

//</editor-fold>
    @EJB
    private ActivityNameRequestsLocal activityNameService;
}
