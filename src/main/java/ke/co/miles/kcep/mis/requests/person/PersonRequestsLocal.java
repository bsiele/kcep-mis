/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;

/**
 *
 * @author siech
 */
@Local
public interface PersonRequestsLocal {

    /**
     *
     * @param personDetails details of the person record to be created
     * @param personRoleDetail details of the person role
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPerson(PersonDetails personDetails, PersonRoleDetail personRoleDetail) throws MilesException;

    /**
     *
     * @param id unique identifier of the person record to retrieve
     * @return the details of the person record retrieved
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public PersonDetails retrievePerson(int id) throws MilesException;

    /**
     *
     * @return the list of person record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrievePeople() throws MilesException;

    /**
     *
     * @param username the person's username
     * @param password the person's password
     * @return the map of person to person role
     * @throws MilesException when the database is in an incorrect state or when
     * the login credentials are invalid or non-existent in the database
     */
    public Map<PersonDetails, PersonRoleDetail> retrievePerson(String username, String password) throws MilesException;

    /**
     *
     * @param personDetails details of the person record to be edited
     * @param personRoleDetail details of the person role
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPerson(PersonDetails personDetails, PersonRoleDetail personRoleDetail) throws MilesException;

    /**
     *
     * @param id unique identifier of the person record to be removed
     * @throws MilesException when the database is in an incorrect state or when
     * the unique identifier is not given
     */
    public void removePerson(int id) throws MilesException;

    /**
     *
     * @param person the person record
     * @return the details of the person record
     */
    public PersonDetails convertPersonToPersonDetails(Person person);

    /**
     *
     * @param subCountyId the unique identifier of the sub-county to which the
     * farmers belong
     * @return the list of retrieved farmers
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveSubCountyFarmers(int subCountyId) throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county to which the people
     * belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveCountyPeople(int countyId) throws MilesException;

    /**
     *
     * @param subCountyId the unique identifier of the sub-county to which the
     * people belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveSubCountyPeople(int subCountyId) throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county to which the people
     * belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveRegionPeople(int countyId) throws MilesException;

    /**
     *
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveKalroPeople() throws MilesException;

    /**
     *
     * @param wardId the unique identifier of the ward to which the people
     * belong
     * @return the list of retrieved people
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PersonDetails> retrieveWardPeople(int wardId) throws MilesException;

}
