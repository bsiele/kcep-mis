/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.ward.WardRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;
import ke.co.miles.kcep.mis.utilities.FarmerSubGroupDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.SexDetail;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;
import ke.co.miles.kcep.mis.utilities.WardDetails;

/**
 *
 * @author siech
 */
@WebServlet(name = "PersonController", urlPatterns = {"/people", "/addPerson", "/doAddPerson", "/userProfile"})
public class PersonController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        //Get the user session
        HttpSession session = request.getSession();

        //Get the user path
        String path = request.getServletPath();
        String destination;

        DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat databaseDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        Date date;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            switch (path) {
                                case "/people":
                                    path = "/head_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/head_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/head_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/head_userProfile";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "kalroSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            switch (path) {
                                case "/people":
                                    path = "/kalro_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/kalro_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/kalro_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/kalro_userProfile";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            switch (path) {
                                case "/people":
                                    path = "/region_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/region_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/region_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/region_userProfile";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            switch (path) {
                                case "/people":
                                    path = "/county_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/county_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/county_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/county_userProfile";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            switch (path) {
                                case "/people":
                                    path = "/sub_county_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/sub_county_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/sub_county_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/sub_county_userProfile";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            switch (path) {
                                case "/people":
                                    path = "/ward_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/ward_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/ward_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/ward_userProfile";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "agroDealerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerson");
                            switch (path) {
                                case "/people":
                                    path = "/agro_dealer_people";
                                    urlPaths.add(path);
                                    break;
                                case "/addPerson":
                                    path = "/agro_dealer_addPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/editPerson":
                                    path = "/agro_dealer_editPerson";
                                    urlPaths.add(path);
                                    break;
                                case "/userProfile":
                                    path = "/agro_dealer_userProfile";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/head_people":

                    List<PersonDetails> people;
                    try {
                        people = personService.retrievePeople();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/ward_people":

                    PersonDetails wao = (PersonDetails) session.getAttribute("person");

                    try {
                        people = personService.retrieveWardPeople(wao.getLocation().getWard().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/kalro_people":

                    try {
                        people = personService.retrieveKalroPeople();
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/region_people":

                    PersonDetails regionalCoordinator = (PersonDetails) session.getAttribute("person");

                    try {
                        people = personService.retrieveRegionPeople(regionalCoordinator.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/county_people":

                    PersonDetails countyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        people = personService.retrieveCountyPeople(countyDeskOfficer.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/sub_county_people":

                    PersonDetails subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        people = personService.retrieveSubCountyPeople(subCountyDeskOfficer.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }

                    List<WardDetails> wards = new ArrayList<>();

                    try {
                        wards.addAll(wardService.retrieveWards(subCountyDeskOfficer.getLocation().getSubCounty().getId()));
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                        return;
                    }

                    session.setAttribute("wards", wards);

                    break;

                case "/agro_dealer_people":

                    PersonDetails agroDealer = (PersonDetails) session.getAttribute("person");

                    try {
                        people = personService.retrieveSubCountyFarmers(agroDealer.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during people retrieval", ex);
                        return;
                    }

                    if (people != null) {
                        session.setAttribute("people", people);
                    }
                    break;

                case "/doAddPerson":

                    ContactDetails contact = new ContactDetails();
                    contact.setEmail(String.valueOf(request.getParameter("email")));
                    contact.setPhone(String.valueOf(request.getParameter("phoneNumber")));
                    contact.setPostalAddress(String.valueOf(request.getParameter("postalAddress")));

                    FarmerGroupDetails farmerGroup = new FarmerGroupDetails();
                    try {
                        farmerGroup.setId(Integer.valueOf(String.valueOf(request.getParameter("farmerGroup"))));
                    } catch (Exception e) {
                        farmerGroup = null;
                    }

                    FarmerSubGroupDetails farmerSubGroup = new FarmerSubGroupDetails();
                    try {
                        farmerSubGroup.setId(Integer.valueOf(String.valueOf(request.getParameter("farmerSubGroup"))));
                    } catch (Exception e) {
                        farmerSubGroup = null;
                    }

                    SubCountyDetails subCounty = new SubCountyDetails();
                    try {
                        subCounty.setId(Integer.valueOf(String.valueOf(request.getParameter("subCounty"))));
                    } catch (Exception e) {
                        subCounty = null;
                    }

                    CountyDetails county = new CountyDetails();
                    try {
                        county.setId(Short.valueOf(String.valueOf(request.getParameter("county"))));
                    } catch (Exception e) {
                        county = null;
                    }

                    WardDetails ward = new WardDetails();
                    try {
                        ward.setId(Integer.valueOf(String.valueOf(request.getParameter("ward"))));
                    } catch (Exception e) {
                        ward = null;
                    }

                    LocationDetails location = new LocationDetails();
                    location.setSubCounty(subCounty);
                    location.setCounty(county);
                    location.setWard(ward);

                    PersonRoleDetail personRole;
                    try {
                        personRole = PersonRoleDetail.getPersonRoleDetail(Short.valueOf(String.valueOf(request.getParameter("personRole"))));
                    } catch (Exception e) {
                        personRole = null;
                    }

                    PersonDetails person = new PersonDetails();
                    person.setBusinessName(String.valueOf(request.getParameter("businessName")));

                    person.setContact(contact);
                    person.setFarmerGroup(farmerGroup);
                    person.setFarmerSubGroup(farmerSubGroup);
                    person.setLocation(location);
                    person.setName(String.valueOf(request.getParameter("name")));
                    person.setNationalId(String.valueOf(request.getParameter("nationalId")));
                    if (person.getBusinessName().equals("null")) {
                        person.setBusinessName(null);
                    }
                    if (person.getNationalId().equals("null")) {
                        person.setNationalId(null);
                    }
                    if (person.getName().equals("null")) {
                        person.setName(null);
                    }
                    if (contact.getEmail().equals("null")) {
                        contact.setEmail(null);
                    }
                    if (contact.getPhone().equals("null")) {
                        contact.setPhone(null);
                    }
                    if (contact.getPostalAddress().equals("null")) {
                        contact.setPostalAddress(null);
                    }
                    try {
                        person.setSex(SexDetail.getSexDetail(Short.valueOf(String.valueOf(request.getParameter("sex")))));
                    } catch (Exception e) {
                        person.setSex(null);
                    }

                    try {
                        //Read in date string in the format MM/dd/yyyy and parse it to date
                        date = userDateFormat.parse(request.getParameter("dateOfBirth"));

                        //Format the date string to yyyy/MM/dd and parse it to date
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));

                        //Set the start date
                        person.setDateOfBirth(date);
                    } catch (ParseException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString("string_parse_error"));
                        person.setDateOfBirth(null);
                    }

                    try {
                        personService.addPerson(person, personRole);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(e.getCode()));
                    }

                    return;

                case "/region_addPerson":
                case "/ward_addPerson":
                case "/kalro_addPerson":
                case "/head_addPerson":
                case "/agro_dealer_addPerson":
                    break;

                case "/county_addPerson":

                    countyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    List<SubCountyDetails> subCounties;
                    try {
                        subCounties = subCountyService.retrieveSubCounties(countyDeskOfficer.getLocation().getCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of sub-counties", ex);
                        return;
                    }

                    wards = new ArrayList<>();
                    if (subCounties != null) {

                        for (SubCountyDetails subCountyDetails : subCounties) {
                            try {
                                wards.addAll(wardService.retrieveWards(subCountyDetails.getId()));
                            } catch (MilesException ex) {
                                LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                                return;
                            }
                        }

                        session.setAttribute("subCounties", subCounties);
                        session.setAttribute("wards", wards);

                    }
                    break;

                case "/sub_county_addPerson":

                    subCountyDeskOfficer = (PersonDetails) session.getAttribute("person");

                    try {
                        wards = wardService.retrieveWards(subCountyDeskOfficer.getLocation().getSubCounty().getId());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of wards", ex);
                        return;
                    }

                    if (wards != null) {
                        session.setAttribute("wards", wards);
                    }

                    break;

                case "/head_userProfile":
                case "/ward_userProfile":
                case "/kalro_userProfile":
                case "/region_userProfile":
                case "/county_userProfile":
                case "/sub_county_userProfile":
                case "/agro_dealer_userProfile":
                    break;

                case "/head_editPerson":
                case "/ward_editPerson":
                case "/kalro_editPerson":
                case "/region_editPerson":
                case "/county_editPerson":
                case "/sub_county_editPerson":
                case "/agro_dealer_editPerson":

                    PersonDetails personDetails = new PersonDetails();
                    personDetails = (PersonDetails) session.getAttribute("person");

                    break;

                default:
                    break;
            }
            //Use request dispatcher to foward request internally
            destination = "/WEB-INF/views" + path + ".jsp";

            LOGGER.log(Level.INFO,
                    "Request dispatch to forward to: {0}", destination);
            try {
                request.getRequestDispatcher(destination).forward(request, response);
            } catch (ServletException | IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(getBundle().getString("redirection_failed") + "<br>");
                LOGGER.log(Level.INFO, getBundle().getString("redirection_failed"), e);

            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString("error_016_02") + "<br>");
            LOGGER.log(Level.INFO, getBundle().getString("error_016_02"));
        }
    }

    private static final Logger LOGGER = Logger.getLogger(PersonController.class.getSimpleName());
    @EJB
    private WardRequestsLocal wardService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private SubCountyRequestsLocal subCountyService;

}
