/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.warehouse;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.equipment.EquipmentRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.WarehouseDetails;

/**
 *
 * @author siech
 */
@Stateless
public class WarehouseRequests extends EntityRequests implements WarehouseRequestsLocal {
//<editor-fold defaultstate="collapsed" desc="Create">  

    @Override
    public int addWarehouse(WarehouseDetails warehouseDetails) throws MilesException {

        if (warehouseDetails == null) {
            throw new InvalidArgumentException("error_008_01");
        }

        Warehouse warehouse;
        q = em.createNamedQuery("Warehouse.findByWarehouseOperatorId");
        q.setParameter("warehouseOperatorId", warehouseDetails.getWarehouseOperator().getId());
        try {
            warehouse = (Warehouse) q.getSingleResult();
        } catch (Exception e) {
            warehouse = null;
        }
        if (warehouse != null) {
            throw new InvalidArgumentException("error_008_02");
        }

        warehouse = new Warehouse();
        warehouse.setWrs(warehouseDetails.getWrs());
        warehouse.setName(warehouseDetails.getName());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setCertified(warehouseDetails.getCertified());

        if (warehouseDetails.getLocation().getId() != null) {
            warehouse.setLocation(em.find(Location.class, warehouseDetails.getLocation().getId()));
        }
        if (warehouseDetails.getUnits().getId() != null) {
            warehouse.setUnits(em.find(MeasurementUnit.class, warehouseDetails.getUnits().getId()));
        }
        if (warehouseDetails.getWarehouseOperator().getId() != null) {
            warehouse.setWarehouseOperator(em.find(Person.class, warehouseDetails.getWarehouseOperator().getId()));
        }

        try {
            em.persist(warehouse);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return warehouse.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public WarehouseDetails retrieveWarehouse(int id) throws MilesException {

        q = em.createNamedQuery("Warehouse.findById");
        q.setParameter("id", id);
        Warehouse warehouse;
        try {
            warehouse = (Warehouse) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehouseToWarehouseDetails(warehouse);

    }

    @Override
    public List<WarehouseDetails> retrieveWarehouses() throws MilesException {

        q = em.createNamedQuery("Warehouse.findAll");
        List<Warehouse> warehouses;
        try {
            warehouses = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWarehousesToWarehouseDetailsList(warehouses);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editWarehouse(WarehouseDetails warehouseDetails) throws MilesException {

        if (warehouseDetails == null) {
            throw new InvalidArgumentException("error_008_01");
        }
        if (warehouseDetails.getId() == null) {
            throw new InvalidArgumentException("error_008_03");
        }

        Warehouse warehouse;
        q = em.createNamedQuery("Warehouse.findByWarehouseOperatorId");
        q.setParameter("warehouseOperatorId", warehouseDetails.getWarehouseOperator().getId());
        try {
            warehouse = (Warehouse) q.getSingleResult();
        } catch (Exception e) {
            warehouse = null;
        }
        if (warehouse != null) {
            if (!warehouse.getId().equals(warehouseDetails.getId())) {
                throw new InvalidArgumentException("error_008_02");
            }
        }

        warehouse = em.find(Warehouse.class, warehouseDetails.getId());
        warehouse.setId(warehouseDetails.getId());
        warehouse.setWrs(warehouseDetails.getWrs());
        warehouse.setName(warehouseDetails.getName());
        warehouse.setCapacity(warehouseDetails.getCapacity());
        warehouse.setCertified(warehouseDetails.getCertified());

        if (warehouseDetails.getLocation()
                .getId() != null) {
            warehouse.setLocation(em.find(Location.class, warehouseDetails.getLocation().getId()));
        }

        if (warehouseDetails.getUnits()
                .getId() != null) {
            warehouse.setUnits(em.find(MeasurementUnit.class, warehouseDetails.getUnits().getId()));
        }

        if (warehouseDetails.getWarehouseOperator()
                .getId() != null) {
            warehouse.setWarehouseOperator(em.find(Person.class, warehouseDetails.getWarehouseOperator().getId()));
        }

        try {
            em.merge(warehouse);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeWarehouse(int id) throws MilesException {

        Warehouse warehouse = em.find(Warehouse.class, id);
        equipmentService.removeEquipment(warehouse.getId());
        try {
            em.remove(warehouse);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    @Override
    public WarehouseDetails convertWarehouseToWarehouseDetails(Warehouse warehouse) {

        LocationDetails locationDetails = null;
        if (warehouse.getLocation().getId() != null) {
            locationDetails = locationService.convertLocationToLocationDetails(warehouse.getLocation());
        }

        MeasurementUnitDetails measurementUnitDetails = null;
        if (warehouse.getUnits().getId() != null) {
            measurementUnitDetails = measurementUnitService.convertMeasurementUnitToMeasurementUnitDetails(warehouse.getUnits());
        }

        PersonDetails warehouseOperatorDetails = null;
        if (warehouse.getWarehouseOperator().getId() != null) {
            warehouseOperatorDetails = personService.convertPersonToPersonDetails(warehouse.getWarehouseOperator());
        }

        WarehouseDetails warehouseDetails = new WarehouseDetails(warehouse.getId());
        warehouseDetails.setWarehouseOperator(warehouseOperatorDetails);
        warehouseDetails.setCertified(warehouse.getCertified());
        warehouseDetails.setCapacity(warehouse.getCapacity());
        warehouseDetails.setUnits(measurementUnitDetails);
        warehouseDetails.setName(warehouse.getName());
        warehouseDetails.setWrs(warehouse.getWrs());
        warehouseDetails.setLocation(locationDetails);

        return warehouseDetails;

    }

    private List<WarehouseDetails> convertWarehousesToWarehouseDetailsList(List<Warehouse> warehouses) {
        List<WarehouseDetails> warehouseDetailsList = new ArrayList<>();
        warehouses.stream().forEach((warehouse) -> {
            warehouseDetailsList.add(convertWarehouseToWarehouseDetails(warehouse));
        });

        return warehouseDetailsList;

    }

//</editor-fold>
    @EJB
    PersonRequestsLocal personService;
    @EJB
    LocationRequestsLocal locationService;
    @EJB
    EquipmentRequestsLocal equipmentService;
    @EJB
    MeasurementUnitRequestsLocal measurementUnitService;

}
