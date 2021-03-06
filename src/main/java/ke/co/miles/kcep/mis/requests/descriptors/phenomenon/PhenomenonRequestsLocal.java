/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.descriptors.phenomenon;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;

/**
 *
 * @author siech
 */
@Local
public interface PhenomenonRequestsLocal {

    /**
     *
     * @param phenomenonDetails details of the phenomenon record to be created
     * @return the unique idenfitifer of the newly created phenomenon
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPhenomenon(PhenomenonDetails phenomenonDetails) throws MilesException;

    /**
     *
     * @param phenomenaDetailsList list of details of the phenomenon records to
     * be created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void addPhenomena(List<PhenomenonDetails> phenomenaDetailsList) throws MilesException;

    /**
     *
     * @return the list of warehouse operators
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveWarehouseOperators() throws MilesException;

    /**
     *
     * @return the list of categories of trainees
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveTraineeCategories() throws MilesException;

    /**
     *
     * @return the list of categories of trainers
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveTrainerCategories() throws MilesException;

    /**
     *
     * @param id the unique identifier of the phenomenon record to be retrieved
     * @return the details of the phenomenon record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PhenomenonDetails retrievePhenomenon(int id) throws MilesException;

    /**
     *
     * @param phenomenonDetails details of the phenomenon record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPhenomenon(PhenomenonDetails phenomenonDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the phenomenon record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePhenomenon(int id) throws MilesException;

    /**
     *
     * @param phenomenon the phenomenon to be converted
     * @return the details of the converted phenomenon
     */
    public PhenomenonDetails convertPhenomenonToPhenomenonDetails(Phenomenon phenomenon);

    /**
     *
     * @return the list of gfss codes retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveGFSSCodes() throws MilesException;

    /**
     *
     * @return the list of sub-components retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveSubComponents() throws MilesException;

    /**
     *
     * @param componentId the unique identifier of the component under which the
     * sub-components to be retrieved belong
     * @return the list of sub-components retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveSubComponents(int componentId) throws MilesException;

    /**
     *
     * @return the list of banks retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveBanks() throws MilesException;

    /**
     *
     * @return the list of performance indicator types
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrievePerformanceIndicatorTypes() throws MilesException;

    /**
     *
     * @return the list of warehouse types
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveWarehouseTypes() throws MilesException;

    /**
     *
     * @return the list of components retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveComponents() throws MilesException;

    /**
     *
     * @return the list of rating values
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveRatingValues() throws MilesException;

    /**
     *
     * @return the list of expected outcomes
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveExpectedOutcomes() throws MilesException;

    /**
     *
     * @return the list of implementing partners
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveImplementingPartners() throws MilesException;

    /**
     *
     * @return the list of response PCU
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveResponsePCUList() throws MilesException;

    /**
     *
     * @return the list of annual indicators
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveAnnualIndicators() throws MilesException;

    /**
     *
     * @return the list of expenditure categories
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PhenomenonDetails> retrieveExpenditureCategories() throws MilesException;

}
