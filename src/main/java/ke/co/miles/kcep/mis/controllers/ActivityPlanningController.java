/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.ActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.name.sub.SubActivityNameRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.annualindicator.AnnualIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.ComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.expenditurecategory.ExpenditureCategoryRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.implementingpartner.ImplementingPartnerRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.responsepcu.ResponsePcuRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.requests.measurementunit.MeasurementUnitRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityNameDetails;
import ke.co.miles.kcep.mis.utilities.AnnualIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
import ke.co.miles.kcep.mis.utilities.ExpenditureCategoryDetails;
import ke.co.miles.kcep.mis.utilities.FinancialYearDetails;
import ke.co.miles.kcep.mis.utilities.ImplementingPartnerDetails;
import ke.co.miles.kcep.mis.utilities.MeasurementUnitDetails;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.ResponsePcuDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityNameDetails;
import ke.co.miles.kcep.mis.utilities.SubComponentDetails;

/**
 *
 * @author siech
 */
@WebServlet(
        name = "PlanningController",
        urlPatterns = {"/activity_names", "/addActivityName", "/doAddActivityName", "/sub_activities",
            "/addSubActivity", "/doAddSubActivity", "/updateSubActivityNames", "/financial_years",
            "/addFinancialYear", "/doAddFinancialYear", "/sub_activity_names", "/addSubActivityName",
            "/doAddSubActivityName", "/doEditActivityName", "/doDeleteActivityName"}
)
public class ActivityPlanningController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession();

        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps
                = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddSubActivity");
                            urlPaths.add("/doAddActivityName");
                            urlPaths.add("/doEditActivityName");
                            urlPaths.add("/doDeleteActivityName");
                            urlPaths.add("/doAddFinancialYear");
                            urlPaths.add("/doAddSubActivityName");
                            urlPaths.add("/updateSubActivityNames");
                            switch (path) {
                                case "/sub_activities":
                                    path = "/head_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/sub_activity_names":
                                    path = "/head_sub_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/activity_names":
                                    path = "/head_activity_names";
                                    urlPaths.add(path);
                                    break;
                                case "/addActivityName":
                                    path = "/head_addActivityName";
                                    urlPaths.add(path);
                                    break;
                                case "/financial_years":
                                    path = "/head_financial_years";
                                    urlPaths.add(path);
                                    break;
                                case "/addFinancialYear":
                                    path = "/head_addFinancialYear";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/head_addSubActivity";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivityName":
                                    path = "/head_addSubActivityName";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPlanning");
                            urlPaths.add("/doAddSubActivity");
                            urlPaths.add("/updateSubActivityNames");
                            switch (path) {
                                case "/sub_activities":
                                    path = "/region_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/region_addSubActivity";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPlanning");
                            urlPaths.add("/doAddSubActivity");
                            urlPaths.add("/updateSubActivityNames");
                            switch (path) {
                                case "/sub_activities":
                                    path = "/county_sub_activities";
                                    urlPaths.add(path);
                                    break;
                                case "/addSubActivity":
                                    path = "/county_addSubActivity";
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

            switch (path) {

                case "/updateSubActivityNames":
                    try {
                        for (SubActivityNameDetails subActivityName : subActivityNameService.
                                retrieveSubActivityNames(Short.valueOf(request.getParameter("activityNameId")))) {
                            out.write("<option value=\"" + subActivityName.getId() + "\">" + subActivityName.getName() + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/head_sub_activity_names":
                    try {
                        short activityNameId = Short.valueOf(request.getParameter("activityNameId"));
                        session.setAttribute("activityNameId", activityNameId);
                        session.setAttribute("subActivityNames", subActivityNameService.
                                retrieveSubActivityNames(activityNameId));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException e) {
                    }
                    break;

                case "/head_financial_years":
                    try {
                        session.setAttribute("financialYears", financialYearService.retrieveFinancialYears());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    break;

                case "/head_activity_names":
                    try {
                        session.setAttribute("activityNames", activityNameService.retrieveActivityNames());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    break;

                case "/doAddActivityName":
                    ActivityNameDetails activity = new ActivityNameDetails();
                    activity.setName(request.getParameter("name"));

                    try {
                        activityNameService.addActivityName(activity);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("activityNames", activityNameService.retrieveActivityNames());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doEditActivityName":
                    try {
                        activity = new ActivityNameDetails(Short.valueOf(request.getParameter("id")));
                        activity.setName(request.getParameter("name"));
                        activityNameService.editActivityName(activity);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }
                    List<ActivityNameDetails> activityNames;

                    try {
                        activityNames = activityNameService.retrieveActivityNames();
                        session.setAttribute("activityNames", activityNames);
                        updateActivityNameTable(response, activityNames);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doDeleteActivityName":
                    try {
                        activityNameService.removeActivityName(Short.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        activityNames = activityNameService.retrieveActivityNames();
                        session.setAttribute("activityNames", activityNames);
                        updateActivityNameTable(response, activityNames);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doAddFinancialYear":
                    FinancialYearDetails financialYear = new FinancialYearDetails();
                    financialYear.setFinancialYear(request.getParameter("financialYear"));
                    financialYear.setCurrentYear(Boolean.valueOf(request.getParameter("current")));

                    try {
                        financialYearService.addFinancialYear(financialYear);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("financialYears", financialYearService.retrieveFinancialYears());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/doAddSubActivityName":
                    SubActivityNameDetails subActivityName = new SubActivityNameDetails();
                    subActivityName.setName(request.getParameter("name"));
                    try {
                        subActivityName.setActivityName(new ActivityNameDetails(
                                Short.valueOf(request.getParameter("activityNameId"))));
                    } catch (Exception e) {
                        subActivityName.setActivityName(null);
                    }

                    try {
                        subActivityNameService.addSubActivityName(subActivityName);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                        return;
                    }

                    try {
                        session.setAttribute("subActivityNames", subActivityNameService.
                                retrieveSubActivityNames(Short.valueOf(request.getParameter("activityNameId"))));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    return;

                case "/head_sub_activities":
                case "/county_sub_activities":
                case "/region_sub_activities":
                    try {
                        session.setAttribute("subActivities", subActivityService.retrieveSubActivities());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    } catch (NumberFormatException ex) {
                        LOGGER.log(Level.INFO, "", ex);

                    }

                    HashMap<SubActivityDetails, List<AnnualIndicatorDetails>> subActivityMap;
                    try {
                        subActivityMap = annualIndicatorService.retrieveSubActivities();
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()));
                        return;
                    }

                    session.setAttribute("subActivityMap", subActivityMap);
                    break;

                case "/head_addSubActivity":
                case "/county_addSubActivity":
                case "/region_addSubActivity":
                    try {
                        session.setAttribute("activityNames", activityNameService.retrieveActivityNames());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        session.setAttribute("financialYears", financialYearService.retrieveFinancialYears());
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    try {
                        session.setAttribute("measurementUnits", measurementUnitService.retrievePlanningMeasurementUnits());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of measurement units", ex);
                        return;
                    }

                    try {
                        session.setAttribute("responsePcuList", responsePcuService.retrieveResponsePcuList());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of the list of response pcu", ex);
                        return;
                    }

                    try {
                        session.setAttribute("expenditureCategories", expenditureCategoryService.retrieveExpenditureCategories());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of expenditure categories", ex);
                        return;
                    }

                    try {
                        session.setAttribute("implementingPartners", implementingPartnerService.retrieveImplementingPartners());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE,
                                "An error occurred during retrieval of implementing partners", ex);
                        return;
                    }

                    try {
                        session.setAttribute("components", componentService.retrieveComponents());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of components", ex);
                        return;
                    }

                    try {
                        session.setAttribute("subComponents", subComponentService.retrieveSubComponents());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of sub-components", ex);
                        return;
                    }

                    try {
                        session.setAttribute("performanceIndicators", performanceIndicatorService.retrievePerformanceIndicators());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of performance indicators", ex);
                        return;
                    }

                    break;

                case "/doAddSubActivity":

                    SubActivityDetails subActivity = new SubActivityDetails();
                    subActivity.setProcurementPlan(request.getParameter("procurementPlan"));
                    subActivity.setExpectedOutcome(request.getParameter("expectedOutcome"));
                    subActivity.setDescription(request.getParameter("description"));
                    subActivity.setAnnualWorkplanReferenceCode(
                            request.getParameter("annualWorkplanReferenceCode"));
                    try {
                        subActivity.setTotals(new BigDecimal(request.getParameter("totals")));
                    } catch (NumberFormatException e) {
                        subActivity.setTotals(null);
                    }
                    try {
                        subActivity.setUnitCost(new BigDecimal(request.getParameter("unitCost")));
                    } catch (NumberFormatException e) {
                        subActivity.setUnitCost(null);
                    }
                    try {
                        subActivity.setAwpbTarget(new BigDecimal(request.getParameter("awpbTarget")));
                    } catch (NumberFormatException e) {
                        subActivity.setAwpbTarget(null);
                    }
                    try {
                        subActivity.setValueAchieved(new BigDecimal(request.getParameter("valueAchieved")));
                    } catch (Exception e) {
                        subActivity.setValueAchieved(null);
                    }
                    try {
                        subActivity.setAllocatedBudget(new BigDecimal(request.getParameter("allocatedBudget")));
                    } catch (Exception e) {
                        subActivity.setAllocatedBudget(null);
                    }
                    try {
                        subActivity.setGokPercentage(new Double(request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setGokPercentage(null);
                    }
                    try {
                        subActivity.setIfadLoanPercentage(new Double(
                                request.getParameter("ifadLoanPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadLoanPercentage(null);
                    }
                    try {
                        subActivity.setIfadGrantPercentage(new Double(
                                request.getParameter("ifadGrantPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadGrantPercentage(null);
                    }
                    try {
                        subActivity.setBeneficiariesPercentage(new Double(
                                request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setBeneficiariesPercentage(null);
                    }
                    try {
                        subActivity.setEuPercentage(new Double(request.getParameter("euPercentage")));
                    } catch (Exception e) {
                        subActivity.setEuPercentage(null);
                    }
                    try {
                        subActivity.setFinancialInstitutionPercentage(new Double(
                                request.getParameter("financialInstitutionPercentage")));
                    } catch (Exception e) {
                        subActivity.setFinancialInstitutionPercentage(null);
                    }
                    try {
                        subActivity.setProgrammeTarget(new BigDecimal(
                                request.getParameter("programmeTarget")));
                    } catch (Exception e) {
                        subActivity.setProgrammeTarget(null);
                    }
                    try {
                        subActivity.setFinancialYear(new FinancialYearDetails(
                                Short.valueOf(request.getParameter("financialYear"))));
                    } catch (NumberFormatException e) {
                        subActivity.setTotals(null);
                    }
                    try {
                        subActivity.setComponent(new ComponentDetails(Short.valueOf(
                                request.getParameter("component"))));
                    } catch (Exception e) {
                        subActivity.setComponent(null);
                    }
                    try {
                        subActivity.setActivityName(new ActivityNameDetails(
                                Short.valueOf(request.getParameter("activityName"))));
                    } catch (Exception e) {
                        subActivity.setActivityName(null);
                    }
                    try {
                        subActivity.setSubComponent(new SubComponentDetails(
                                Short.valueOf(request.getParameter("subComponent"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setResponsePcu(new ResponsePcuDetails(
                                Short.valueOf(request.getParameter("responsePcu"))));
                    } catch (Exception e) {
                        subActivity.setResponsePcu(null);
                    }
                    try {
                        subActivity.setSubActivityName(new SubActivityNameDetails(
                                Short.valueOf(request.getParameter("subActivityName"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setExpenditureCategory(new ExpenditureCategoryDetails(
                                Short.valueOf(request.getParameter("expenditureCategory"))));
                    } catch (Exception e) {
                        subActivity.setExpenditureCategory(null);
                    }
                    try {
                        subActivity.setImplementingPartner(new ImplementingPartnerDetails(
                                Short.valueOf(request.getParameter("implementingPartner"))));
                    } catch (Exception e) {
                        subActivity.setImplementingPartner(null);
                    }
                    try {
                        subActivity.setPerformanceIndicator(new PerformanceIndicatorDetails(
                                Short.valueOf(request.getParameter("performanceIndicator"))));
                    } catch (Exception e) {
                        subActivity.setPerformanceIndicator(null);
                    }
                    try {
                        subActivity.setMeasurementUnit(new MeasurementUnitDetails(
                                Short.valueOf(request.getParameter("measurementUnit"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("startDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setStartDate(date);
                    } catch (Exception ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_error"), ex);
                        subActivity.setStartDate(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("endDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setEndDate(date);
                    } catch (Exception ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_error"), ex);
                        subActivity.setEndDate(null);
                    }
                    String[] annualIndicatorIds = String.valueOf(request.getParameter("annualIndicatorIds")).split("-");
                    AnnualIndicatorDetails annualIndicatorRecord;
                    List<AnnualIndicatorDetails> annualIndicatorRecords = new ArrayList<>();

                    try {
                        subActivity.setId(subActivityService.addSubActivity(subActivity));
                        for (String annualIndicatorId : annualIndicatorIds) {
                            PerformanceIndicatorDetails performanceIndicator = new PerformanceIndicatorDetails();
                            annualIndicatorRecord = new AnnualIndicatorDetails();
                            try {
                                performanceIndicator.setId(Short.valueOf(annualIndicatorId));
                                annualIndicatorRecord.setPerformanceIndicator(performanceIndicator);
                                annualIndicatorRecord.setSubActivity(subActivity);
                                annualIndicatorRecords.add(annualIndicatorRecord);
                            } catch (Exception e) {
                            }
                        }
                        annualIndicatorService.addAnnualIndicators(annualIndicatorRecords);

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }
                    break;
                    
                case "/doEditSubActivity":

                     subActivity = new SubActivityDetails();
                    try {
                        subActivity.setId(Integer.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }
                    subActivity.setProcurementPlan(request.getParameter("procurementPlan"));
                    subActivity.setExpectedOutcome(request.getParameter("expectedOutcome"));
                    subActivity.setDescription(request.getParameter("description"));
                    subActivity.setAnnualWorkplanReferenceCode(
                            request.getParameter("annualWorkplanReferenceCode"));
                    try {
                        subActivity.setTotals(new BigDecimal(request.getParameter("totals")));
                    } catch (NumberFormatException e) {
                        subActivity.setTotals(null);
                    }
                    try {
                        subActivity.setUnitCost(new BigDecimal(request.getParameter("unitCost")));
                    } catch (NumberFormatException e) {
                        subActivity.setUnitCost(null);
                    }
                    try {
                        subActivity.setAwpbTarget(new BigDecimal(request.getParameter("awpbTarget")));
                    } catch (NumberFormatException e) {
                        subActivity.setAwpbTarget(null);
                    }
                    try {
                        subActivity.setValueAchieved(new BigDecimal(request.getParameter("valueAchieved")));
                    } catch (Exception e) {
                        subActivity.setValueAchieved(null);
                    }
                    try {
                        subActivity.setAllocatedBudget(new BigDecimal(request.getParameter("allocatedBudget")));
                    } catch (Exception e) {
                        subActivity.setAllocatedBudget(null);
                    }
                    try {
                        subActivity.setGokPercentage(new Double(request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setGokPercentage(null);
                    }
                    try {
                        subActivity.setIfadLoanPercentage(new Double(
                                request.getParameter("ifadLoanPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadLoanPercentage(null);
                    }
                    try {
                        subActivity.setIfadGrantPercentage(new Double(
                                request.getParameter("ifadGrantPercentage")));
                    } catch (Exception e) {
                        subActivity.setIfadGrantPercentage(null);
                    }
                    try {
                        subActivity.setBeneficiariesPercentage(new Double(
                                request.getParameter("gokPercentage")));
                    } catch (Exception e) {
                        subActivity.setBeneficiariesPercentage(null);
                    }
                    try {
                        subActivity.setEuPercentage(new Double(request.getParameter("euPercentage")));
                    } catch (Exception e) {
                        subActivity.setEuPercentage(null);
                    }
                    try {
                        subActivity.setFinancialInstitutionPercentage(new Double(
                                request.getParameter("financialInstitutionPercentage")));
                    } catch (Exception e) {
                        subActivity.setFinancialInstitutionPercentage(null);
                    }
                    try {
                        subActivity.setProgrammeTarget(new BigDecimal(
                                request.getParameter("programmeTarget")));
                    } catch (Exception e) {
                        subActivity.setProgrammeTarget(null);
                    }
                    try {
                        subActivity.setFinancialYear(new FinancialYearDetails(
                                Short.valueOf(request.getParameter("financialYear"))));
                    } catch (NumberFormatException e) {
                        subActivity.setTotals(null);
                    }
                    try {
                        subActivity.setComponent(new ComponentDetails(Short.valueOf(
                                request.getParameter("component"))));
                    } catch (Exception e) {
                        subActivity.setComponent(null);
                    }
                    try {
                        subActivity.setActivityName(new ActivityNameDetails(
                                Short.valueOf(request.getParameter("activityName"))));
                    } catch (Exception e) {
                        subActivity.setActivityName(null);
                    }
                    try {
                        subActivity.setSubComponent(new SubComponentDetails(
                                Short.valueOf(request.getParameter("subComponent"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setResponsePcu(new ResponsePcuDetails(
                                Short.valueOf(request.getParameter("responsePcu"))));
                    } catch (Exception e) {
                        subActivity.setResponsePcu(null);
                    }
                    try {
                        subActivity.setSubActivityName(new SubActivityNameDetails(
                                Short.valueOf(request.getParameter("subActivityName"))));
                    } catch (Exception e) {
                        subActivity.setSubComponent(null);
                    }
                    try {
                        subActivity.setExpenditureCategory(new ExpenditureCategoryDetails(
                                Short.valueOf(request.getParameter("expenditureCategory"))));
                    } catch (Exception e) {
                        subActivity.setExpenditureCategory(null);
                    }
                    try {
                        subActivity.setImplementingPartner(new ImplementingPartnerDetails(
                                Short.valueOf(request.getParameter("implementingPartner"))));
                    } catch (Exception e) {
                        subActivity.setImplementingPartner(null);
                    }
                    try {
                        subActivity.setPerformanceIndicator(new PerformanceIndicatorDetails(
                                Short.valueOf(request.getParameter("performanceIndicator"))));
                    } catch (Exception e) {
                        subActivity.setPerformanceIndicator(null);
                    }
                    try {
                        subActivity.setMeasurementUnit(new MeasurementUnitDetails(
                                Short.valueOf(request.getParameter("measurementUnit"))));
                    } catch (Exception e) {
                        subActivity.setMeasurementUnit(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("startDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setStartDate(date);
                    } catch (Exception ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_error"), ex);
                        subActivity.setStartDate(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("endDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        subActivity.setEndDate(date);
                    } catch (Exception ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString("string_parse_error") + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString("string_parse_error"), ex);
                        subActivity.setEndDate(null);
                    }
                
                 annualIndicatorIds = String.valueOf(request.getParameter("annualIndicatorIds")).split("-");
                     annualIndicatorRecords = new ArrayList<>();

                    try {
                        subActivity.setId(subActivityService.addSubActivity(subActivity));
                        for (String annualIndicatorId : annualIndicatorIds) {
                            PerformanceIndicatorDetails performanceIndicator = new PerformanceIndicatorDetails();
                            annualIndicatorRecord = new AnnualIndicatorDetails();
                            try {
                                performanceIndicator.setId(Short.valueOf(annualIndicatorId));
                                annualIndicatorRecord.setPerformanceIndicator(performanceIndicator);
                                annualIndicatorRecord.setSubActivity(subActivity);
                                annualIndicatorRecords.add(annualIndicatorRecord);
                            } catch (Exception e) {
                            }
                        }
                        annualIndicatorService.addAnnualIndicators(annualIndicatorRecords);

                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.INFO, "", ex);
                    }

                    return;

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

    //<editor-fold defaultstate="collapsed" desc="Update tables">
    private void updateActivityNameTable(HttpServletResponse response, List<ActivityNameDetails> activityNames) throws IOException {
        PrintWriter out = response.getWriter();
        int index = 0;
        for (ActivityNameDetails activityName : activityNames) {
            if (index % 2 == 0) {
                out.write("<tr class=\"odd\">");
            } else {
                out.write("<tr>");
            }
            out.write(" <td>" + ++index + "</td>\n"
                    + "                                            <td class=\"pointable\" onclick=\"loadSubActivityNamesWindow(" + activityName.getId() + ")\">" + activityName.getName() + "</td>\n"
                    + "                                            <td><button onclick=\"editActivityName('" + activityName.getId() + "', '" + activityName.getName() + "')\"><span class=\"glyphicon glyphicon-pencil\"></span></button></td>\n"
                    + "                                            <td><button onclick=\"deleteActivityName('" + activityName.getId() + "')\"><span class=\"glyphicon glyphicon-trash\"></span></button></td>\n"
                    + "                                        </tr>");
        }
    }
    //</editor-fold>
    private static final Logger LOGGER = Logger.getLogger(ActivityPlanningController.class
            .getSimpleName());

    @EJB
    private ActivityNameRequestsLocal activityNameService;
    @EJB
    private SubActivityRequestsLocal subActivityService;
    @EJB
    private ComponentRequestsLocal componentService;
    @EJB
    private ResponsePcuRequestsLocal responsePcuService;
    @EJB
    private FinancialYearRequestsLocal financialYearService;
    @EJB
    private SubComponentRequestsLocal subComponentService;
    @EJB
    private AnnualIndicatorRequestsLocal annualIndicatorService;
    @EJB
    private SubActivityNameRequestsLocal subActivityNameService;
    @EJB
    private MeasurementUnitRequestsLocal measurementUnitService;
    @EJB
    private ImplementingPartnerRequestsLocal implementingPartnerService;
    @EJB
    private ExpenditureCategoryRequestsLocal expenditureCategoryService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
}
