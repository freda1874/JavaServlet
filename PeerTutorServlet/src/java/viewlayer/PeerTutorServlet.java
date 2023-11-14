package viewlayer;

import businesslayer.PeerTutorBusinessLogic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transferobject.PeerTutor;

/**
 * Servlet responsible for handling requests related to Peer Tutor operations.
 * It communicates with the business logic layer to process peer tutor validations
 * and course assignments.
 * 
 * @author: Lei Luo
 * @version 1.0
 * @since JDK 11
 */
public class PeerTutorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP {@code GET} and {@code POST} methods.
     * Validates peer tutor information and course assignments, and generates
     * appropriate HTML output in response to the client's request.
     *
     * @param request  Servlet request
     * @param response Servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>A Sample Form Using POST</title>");
            out.println("</head>");
            out.println("<body bgcolor=\"#FDF5E6\">");
            out.println("<h1>Servlet PeerTutorServlet at " + request.getContextPath() + "</h1>");

            PeerTutorBusinessLogic logic = new PeerTutorBusinessLogic();
            String courseCode = request.getParameter("code");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            PeerTutor peerTutor = new PeerTutor();
            peerTutor.setFirstName(firstName);
            peerTutor.setLastName(lastName);

            if (logic.isPeerTutorRegistered(peerTutor) == false) {
                out.println("<ul>");
                out.println("<li> LastName: " + lastName + "</li>");
                out.println("<li> FirstName: " + firstName + "</li>");
                out.println("</ul>");
                out.println("<h2> Error: The person is not registered as a peer tutor</h2> ");
            } else if (logic.isCourseValid(courseCode) == false) {
                out.println("<ul>");
                out.println("<li> Course Code:" + courseCode + "</li>");
                out.println("</ul>");
                out.println("<h2> Error: The course is not valid</h2> ");
            } else if (logic.hasPeerTutorTakenCourse(peerTutor, courseCode) == false) {
                   out.println("<ul>");
                out.println("<li> LastName:" + lastName + "</li>");
                out.println("<li> FirstName:" + firstName + "</li>");
                out.println("<li> Course Code:" + courseCode + "</li>");
                  out.println("</ul>");
                out.println("<h2> Error: The peer tutor has not taken the course</h2> ");
            } else if (logic.getPeerTutorLetterGradeForCourse(peerTutor, courseCode) == false) {

                 out.println("<ul>");
                out.println("<li> LastName:" + lastName + "</li>");
                out.println("<li> FirstName:" + firstName + "</li>");
                out.println("<li> Course Code:" + courseCode + "</li>");
                   out.println("</ul>");
                out.println("<h2> Error: The letter grade obtained by the peer tutor for the course is not sufficient</h2> ");
            } else if (logic.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode) == true) {
                   out.println("<ul>");
                out.println("<li> LastName:" + lastName + "</li>");
                out.println("<li> FirstName:" + firstName + "</li>");
                out.println("<li> Course Code:" + courseCode + "</li>");
                  out.println("</ul>");
                out.println("<h2> Error: The peer tutor is already assigned to the course</h2> ");
            } else {

                logic.assignCourseToPeerTutor(peerTutor, courseCode);
                List<PeerTutor> tutors = logic.getAllPeerTutorsForCourse(courseCode);
                out.println("<table>");
                out.println("<caption>Table of Peer Tutors for " + courseCode + "</caption>");
                out.println("<tr><th>Tutor ID</th><th>Last Name</th><th>First Name</th></tr>");
                for (PeerTutor tutor : tutors) {
                    out.printf("<tr><td>%s</td><td>%s</td></tr>",
                            tutor.getLastName(), tutor.getFirstName());
                    out.println("</table>");
                }
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
