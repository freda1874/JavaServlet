/* File: PeerTutorBusinessLogic.java
 * Author: Lei Luo
 * Date: 2023
 * Description: Demonstration of DAO Design Pattern with Servlet website
 */
package businesslayer;

import dataaccesslayer.PeerTutorDAO;
import dataaccesslayer.PeerTutorDAOImpl;
import java.util.List;

import transferobject.PeerTutor;

/**
 * This class represents the business logic layer for the Peer Tutor management
 * system. It interacts with the data access layer to perform various operations
 * related to peer tutors.
 */
public class PeerTutorBusinessLogic {

    
/**
     * Constructs a new PeerTutorBusinessLogic object, initializing the DAO
     * implementation.
     */
    private PeerTutorDAO peerTutorDAO;

    /**
     * Constructs a new PeerTutorBusinessLogic object, initializing the DAO
     * implementation.
     */
    public PeerTutorBusinessLogic() {
        peerTutorDAO = new PeerTutorDAOImpl();
    }

    /**
     * Validates if a peer tutor is registered in the system.
     *
     * @param peerTutor The peer tutor to be validated.
     *  @return true if the peer tutor is registered, otherwise false.
     *
     */
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {

        return peerTutorDAO.isPeerTutorRegistered(peerTutor);
    }

    /**
     * Validates if a given course code is valid within the system.
     *
     *@param courseCode The course code to validate.
     * @return true if the course code is valid, otherwise false.
     *
     */
    public boolean isCourseValid(String courseCode) {

        return peerTutorDAO.isCourseValid(courseCode);
    }

   /**
     * Verifies whether a peer tutor has previously taken a course identified by the course code.
     *
     * @param peerTutor  The peer tutor in question.
     * @param courseCode The code of the course to check.
     * @return true if the peer tutor has taken the course, otherwise false.
     */
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {

        return peerTutorDAO.hasPeerTutorTakenCourse(peerTutor, courseCode);
    }

    /**
     * Retrieves the letter grade that a peer tutor received for a particular course.
     *
     * @param peerTutor  The peer tutor whose grade is requested.
     * @param courseCode The course code for which the grade is needed.
     * @return A String representing the grade received, or null if no grade is found.
     */
    public boolean getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {
        String grade = peerTutorDAO.getPeerTutorLetterGradeForCourse(peerTutor, courseCode);
        return "A".equals(grade) || "A-".equals(grade) || "A+".equals(grade);
        
    }
/**
     * Determines if a course has already been assigned to a peer tutor.
     *
     * @param peerTutor  The peer tutor to be checked.
     * @param courseCode The course code to be verified against the peer tutor's assignments.
     * @return true if the course is already assigned to the peer tutor, otherwise false.
     */
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {

        return peerTutorDAO.isCourseAlreadyAssignedToPeerTutor(peerTutor, courseCode);
    }

    /**
     * Assigns a peer tutor to a course after validating all required conditions.
     *
     * @param peerTutor  The peer tutor to whom the course will be assigned.
     * @param courseCode The code of the course to be assigned.
     */
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {

        // All validations passed, proceed with the assignment
        peerTutorDAO.assignCourseToPeerTutor(peerTutor, courseCode);
    }

      /**
     * Retrieves a list of all peer tutors who are assigned to a specific course.
     *
     * @param courseCode The code of the course for which peer tutors are to be retrieved.
     * @return A List of PeerTutor objects who are assigned to the course.
     */
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {

        return peerTutorDAO.getAllPeerTutorsForCourse(courseCode);
    }
}
