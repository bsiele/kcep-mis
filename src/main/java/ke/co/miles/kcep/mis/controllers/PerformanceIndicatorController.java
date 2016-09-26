/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import ke.co.miles.kcep.mis.requests.activityplanning.component.sub.SubComponentRequestsLocal;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.PhenomenonRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.hierarchy.ResultHierarchyRequestsLocal;
import ke.co.miles.kcep.mis.requests.logframe.performanceindicator.PerformanceIndicatorRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.ResultHierarchyDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author siech
 */
@WebServlet(name = "PerformanceIndicatorController",
        urlPatterns = {"/performance_indicators", "/addPerformanceIndicator",
            "/doEditPerformanceIndicator", "/doDeletePerformanceIndicator",
            "/doAddPerformanceIndicator", "/doEditPerformanceIndicatorValues"})
public class PerformanceIndicatorController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession();
        String path = request.getServletPath();
        String destination;

        DecimalFormat decimalFormat = new DecimalFormat("######.##");

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
                            urlPaths.add("/doAddPerformanceIndicator");
                            urlPaths.add("/doEditPerformanceIndicatorValues");
                            urlPaths.add("/doEditPerformanceIndicator");
                            urlPaths.add("/doDeletePerformanceIndicator");
                            if (path.equals("/performance_indicators")) {
                                path = "/head_performance_indicators";
                                urlPaths.add(path);
                            } else if (path.equals("/addPerformanceIndicator")) {
                                path = "/head_addPerformanceIndicator";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerformanceIndicator");
                            urlPaths.add("/doEditPerformanceIndicatorValues");
                            urlPaths.add("/doEditPerformanceIndicator");
                            urlPaths.add("/doDeletePerformanceIndicator");
                            if (path.equals("/performance_indicators")) {
                                path = "/region_performance_indicators";
                                urlPaths.add(path);
                            } else if (path.equals("/addPerformanceIndicator")) {
                                path = "/region_addPerformanceIndicator";
                                urlPaths.add(path);
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddPerformanceIndicator");
                            urlPaths.add("/doEditPerformanceIndicatorValues");
                            urlPaths.add("/doEditPerformanceIndicator");
                            urlPaths.add("/doDeletePerformanceIndicator");
                            if (path.equals("/performance_indicators")) {
                                path = "/county_performance_indicators";
                                urlPaths.add(path);
                            } else if (path.equals("/addPerformanceIndicator")) {
                                path = "/county_addPerformanceIndicator";
                                urlPaths.add(path);
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

                case "/doEditPerformanceIndicatorValues":

                    PerformanceIndicatorDetails performanceIndicator
                            = new PerformanceIndicatorDetails();
                    try {
                        performanceIndicator.setId(Short.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }

                    try {
                        performanceIndicator.setExpectedValue(Double.valueOf(request.getParameter("expectedValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setExpectedValue(null);
                    }

                    try {
                        performanceIndicator.setBaselineValue(Double.valueOf(request.getParameter("baselineValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setBaselineValue(null);
                    }

                    try {
                        performanceIndicator.setRatio(Double.valueOf(request.getParameter("ratio")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setRatio(null);
                        try {
                            performanceIndicator.setRatio(Double.
                                    parseDouble(decimalFormat.format((performanceIndicator.getActualValue()
                                            / performanceIndicator.getExpectedValue()) * 100
                                    )));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    try {
                        date = userDateFormat.parse(request.getParameter("baselineDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        performanceIndicator.setBaselineDate(date);
                    } catch (ParseException e) {
                        response.getWriter().write(getBundle().getString("string_parse_error"));
                        LOGGER.log(Level.WARNING, getBundle().getString("string_parse_error"), e);
                        performanceIndicator.setBaselineDate(null);
                    }

                    try {
                        performanceIndicatorService.editPerformanceIndicatorValues(performanceIndicator);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/performanceIndicators":

                    try {
                        List<PerformanceIndicatorDetails> doEditPerformanceIndicatorValues
                                = performanceIndicatorService.retrievePerformanceIndicators();

                        JSONObject jsonObject = new JSONObject();
                        JSONArray list;
                        for (PerformanceIndicatorDetails pi : doEditPerformanceIndicatorValues) {
                            list = new JSONArray();
                            list.add(pi.getPerformanceIndicatorType().getCategory().getName());
                            list.add(pi.getResultHierarchy().getDescription());
                            list.add(pi.getDescription());

                        }

                        response.setContentType("application/json");
                        response.getWriter().write(jsonObject.toJSONString());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during performance indicator retrieval", ex);
                        return;
                    }

                    return;

                case "/head_performance_indicators":
                case "/county_performance_indicators":
                case "/region_performance_indicators":

                    try {
                        session.setAttribute("performanceIndicators",
                                performanceIndicatorService.retrievePerformanceIndicators());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during performance indicator retrieval", ex);
                        return;
                    }

                    try {
                        session.setAttribute("performanceIndicatorTypes",
                                phenomenonService.retrievePerformanceIndicatorTypes());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of performance indicator types ", ex);
                        return;
                    }

                    try {
                        session.setAttribute("resultHierarchies", resultHierarchyService.retrieveResultHierarchies());
                    } catch (MilesException ex) {
                        LOGGER.log(Level.SEVERE, "An error occurred during retrieval of result hierarchies ", ex);
                        return;
                    }

                    break;

                case "/doAddPerformanceIndicator":

                    performanceIndicator = new PerformanceIndicatorDetails();
                    performanceIndicator.setDescription(request.getParameter("description"));

                    try {
                        performanceIndicator.setExpectedValue(Double.valueOf(request.getParameter("expectedValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setExpectedValue(null);
                    }

                    try {
                        performanceIndicator.setBaselineValue(Double.valueOf(request.getParameter("baselineValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setBaselineValue(null);
                    }

                    try {
                        performanceIndicator.setActualValue(Double.valueOf(request.getParameter("actualValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setActualValue(null);
                    }

                    try {
                        performanceIndicator.setRatio(Double.valueOf(request.getParameter("ratio")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setRatio(null);
                        try {
                            performanceIndicator.setRatio(Double.
                                    parseDouble(decimalFormat.format((performanceIndicator.getActualValue()
                                            / performanceIndicator.getExpectedValue()) * 100
                                    )));
                        } catch (NumberFormatException ex) {
                        }

                    }

                    ResultHierarchyDetails resultHierarchy;
                    try {
                        resultHierarchy = new ResultHierarchyDetails(Short.valueOf(request.getParameter("resultHierarchy")));
                    } catch (Exception e) {
                        resultHierarchy = null;
                    }

                    PhenomenonDetails performanceIndicatorType;
                    try {
                        performanceIndicatorType = new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("performanceIndicatorType")));
                    } catch (Exception e) {
                        performanceIndicatorType = null;
                    }

                    performanceIndicator.setResultHierarchy(resultHierarchy);
                    performanceIndicator.setPerformanceIndicatorType(performanceIndicatorType);

                    try {
                        date = userDateFormat.parse(request.getParameter("baselineDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        performanceIndicator.setBaselineDate(date);
                    } catch (ParseException e) {
                        response.getWriter().write(getBundle().getString("string_parse_error"));
                        LOGGER.log(Level.WARNING, getBundle().getString("string_parse_error"), e);
                        performanceIndicator.setBaselineDate(null);
                    }

                    try {
                        performanceIndicator.setYearOfUse(Short.valueOf(request.getParameter("yearOfUse")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setYearOfUse(null);
                    }

                    try {
                        performanceIndicatorService.addPerformanceIndicator(performanceIndicator);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doEditPerformanceIndicator":

                    performanceIndicator = new PerformanceIndicatorDetails();
                    try {
                        performanceIndicator.setId(Short.valueOf(request.getParameter("id")));
                    } catch (Exception e) {
                    }
                    performanceIndicator.setDescription(request.getParameter("description"));

                    try {
                        performanceIndicator.setExpectedValue(Double.valueOf(request.getParameter("expectedValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setExpectedValue(null);
                    }

                    try {
                        performanceIndicator.setBaselineValue(Double.valueOf(request.getParameter("baselineValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setBaselineValue(null);
                    }

                    try {
                        performanceIndicator.setActualValue(Double.valueOf(request.getParameter("actualValue")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setActualValue(null);
                    }

                    try {
                        performanceIndicator.setRatio(Double.valueOf(request.getParameter("ratio")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setRatio(null);
                        try {
                            performanceIndicator.setRatio(Double.
                                    parseDouble(decimalFormat.format((performanceIndicator.getActualValue()
                                            / performanceIndicator.getExpectedValue()) * 100
                                    )));
                        } catch (NumberFormatException ex) {
                        }
                    }

                    try {
                        resultHierarchy = new ResultHierarchyDetails(Short.valueOf(request.getParameter("resultHierarchy")));
                    } catch (Exception e) {
                        resultHierarchy = null;
                    }

                    try {
                        performanceIndicatorType = new PhenomenonDetails(
                                Integer.valueOf(request.getParameter("performanceIndicatorType")));
                    } catch (Exception e) {
                        performanceIndicatorType = null;
                    }

                    performanceIndicator.setResultHierarchy(resultHierarchy);
                    performanceIndicator.setPerformanceIndicatorType(performanceIndicatorType);

                    try {
                        date = userDateFormat.parse(request.getParameter("baselineDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        performanceIndicator.setBaselineDate(date);
                    } catch (ParseException e) {
                        response.getWriter().write(getBundle().getString("string_parse_error"));
                        LOGGER.log(Level.WARNING, getBundle().getString("string_parse_error"), e);
                        performanceIndicator.setBaselineDate(null);
                    }

                    try {
                        performanceIndicator.setYearOfUse(Short.valueOf(request.getParameter("yearOfUse")));
                    } catch (NumberFormatException e) {
                        performanceIndicator.setYearOfUse(null);
                    }

                    try {
                        performanceIndicatorService.editPerformanceIndicator(performanceIndicator);
                    } catch (MilesException e) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(e.getCode()));
                        LOGGER.log(Level.INFO, "", e);
                    }

                    return;

                case "/doDeletePerformanceIndicator":
                    try {
                        performanceIndicatorService.removePerformanceIndicator(Integer.valueOf(request.getParameter("id")));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.SEVERE, getBundle().getString(ex.getCode()));
                    }

                    return;

                default:
                    break;
            }
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

    private static final Logger LOGGER = Logger.getLogger(PerformanceIndicatorController.class
            .getSimpleName());

    @EJB
    private PhenomenonRequestsLocal phenomenonService;
    @EJB
    private ResultHierarchyRequestsLocal resultHierarchyService;
    @EJB
    private PerformanceIndicatorRequestsLocal performanceIndicatorService;
    @EJB
    private SubComponentRequestsLocal subComponentService;

}
