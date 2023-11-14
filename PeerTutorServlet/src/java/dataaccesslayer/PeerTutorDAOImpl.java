/* *File: PeerTutorDAOImpl.java
 * Author: Lei Luo  
 * Date: 2023
 * Description: Demonstration of DAO Design Pattern with Servlet website
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import transferobject.PeerTutor;

/**
 * Implementation of the PeerTutorDAO interface. This class handles the data
 * access operations related to peer tutors, interfacing with a SQL database.
 * @author: Lei Luo
 * @version 1.0
 * @since JDK 11
 */
public class PeerTutorDAOImpl implements PeerTutorDAO {

    /**
     * Checks if a peer tutor is registered in the database.
     *
     * @param peerTutor The peer tutor to be checked.
     * @return true if the peer tutor is registered, false otherwise.
     */
    @Override
    public boolean isPeerTutorRegistered(PeerTutor peerTutor) {

        // if the person is not registered as a peer tutor, then display an error message
        boolean isRegistered = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            DataSource ds = new DataSource();

            con = ds.createConnection();
            if (con == null) {
                throw new SQLException("Could not establish database connection");
            }
            pstmt = con.prepareStatement("SELECT * FROM peertutor WHERE firstName = ? and lastName=?");
            pstmt.setString(1, peerTutor.getFirstName());
            pstmt.setString(2, peerTutor.getLastName());
            rs = pstmt.executeQuery();

            while (rs.next()) {

                isRegistered = true;// Peer tutor is registered because a record was found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return isRegistered;

    }

    /**
     * Checks if a course code is valid and exists in the database.
     *
     * @param courseCode The course code to be validated.
     * @return true if the course code is valid, false otherwise.
     */
    @Override
    public boolean isCourseValid(String courseCode) {

        boolean isCourseValid = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM course WHERE CourseCode = ?");
            pstmt.setString(1, courseCode);
            rs = pstmt.executeQuery(); // Execute the query

            if (rs.next()) {

                isCourseValid = true;// Peer tutor is registered because a record was found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return isCourseValid;
    }

    /**
     * Checks if a peer tutor has taken a specific course.
     *
     * @param peerTutor The peer tutor to be checked.
     * @param courseCode The course code to check against the tutor.
     * @return true if the peer tutor has taken the course, false otherwise.
     */
    @Override
    public boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.

        boolean hasPeerTutorTakenCourse = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();

            pstmt = con.prepareStatement(
                    "SELECT * FROM course join StudentCourse on course.CourseCode=StudentCourse.Course_CourseCode join  "
                    + "Student on StudentCourse.Student_StudentID=Student.StudentID "
                    + "WHERE CourseCode = ?  and student.FirstName=? and  student.LastName=?");
            pstmt.setString(1, courseCode);
            pstmt.setString(2, peerTutor.getFirstName());
            pstmt.setString(3, peerTutor.getLastName());
            rs = pstmt.executeQuery();
            if (rs.next()) {

                hasPeerTutorTakenCourse = true;// Peer tutor is registered because a record was found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return hasPeerTutorTakenCourse;

    }

    /**
     * Retrieves the letter grade a peer tutor obtained in a specific course.
     *
     * @param peerTutor The peer tutor whose grade is to be retrieved.
     * @param courseCode The course code for which the grade is required.
     * @return The letter grade obtained by the peer tutor, or an error message
     * if the grade is insufficient.
     */
    @Override
    public String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode) {

        String PeerTutorLetterGradeForCourse = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT distinct(grade.GradeCode)as GradeCode FROM  grade join  student on grade.Student_StudentID =student.StudentID join peertutor on peertutor.LastName=student. LastName and peertutor.FirstName=student.FirstName and peertutor.Email=student.Email where "
                    + "peertutor.FirstName=? and peertutor.LastName=? and grade.Course_CourseCode=?");

            pstmt.setString(1, peerTutor.getFirstName());
            pstmt.setString(2, peerTutor.getLastName());
            pstmt.setString(3, courseCode);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                PeerTutorLetterGradeForCourse = rs.getString("GradeCode");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return PeerTutorLetterGradeForCourse;
    }

    /**
     * Checks if a course is already assigned to a peer tutor.
     *
     * @param peerTutor The peer tutor to check for course assignment.
     * @param courseCode The course code to be checked.
     * @return true if the course is already assigned to the peer tutor, false
     * otherwise.
     */
    @Override
    public boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // the peer tutor is already assigned to the course, then display an error message
        boolean isCourseAlreadyAssignedToPeerTutor = false;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement(
                    "SELECT * FROM peertutorcourse join peertutor on  peertutorcourse.PeerTutor_PeerTutorID=peertutor.PeerTutorID"
                    + " WHERE peertutor.LastName = ? and peertutor.FirstName = ?  and Course_CourseCode=? ");

            pstmt.setString(2, peerTutor.getFirstName());
            pstmt.setString(1, peerTutor.getLastName());
            pstmt.setString(3, courseCode);
            rs = pstmt.executeQuery();
            while (rs.next()) {

                isCourseAlreadyAssignedToPeerTutor = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return isCourseAlreadyAssignedToPeerTutor;
    }

    /**
     * Assigns a course to a peer tutor.
     *
     * @param peerTutor The peer tutor to whom the course is to be assigned.
     * @param courseCode The course code to be assigned.
     */
    @Override
    public void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCode) {
        // assign the course to the peer tutor

        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();

            pstmt = con.prepareStatement(
                    "INSERT INTO PeerTutorCourse (PeerTutor_PeerTutorID, Course_CourseCode) "
                            + "SELECT PeerTutorID,grade.Course_CourseCode FROM peertutor join student on peertutor.LastName=student. LastName and peertutor.FirstName=student.FirstName and peertutor.Email=student.Email join  grade on grade.Student_StudentID =student.StudentID "
                            + "WHERE peertutor.FirstName=? and peertutor.LastName=? and grade.Course_CourseCode=?");

            pstmt.setString(1, peerTutor.getLastName());
            pstmt.setString(2, peerTutor.getFirstName());
            pstmt.setString(3, courseCode);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Retrieves a list of all peer tutors assigned to a specific course.
     *
     * @param courseCode The course code for which peer tutors are to be
     * retrieved.
     * @return A list of peer tutors assigned to the specified course.
     */
    @Override
    public List<PeerTutor> getAllPeerTutorsForCourse(String courseCode) {
        // TODO:  Add your code here.  Be sure to use try-catch-finally statement.
        //       display a table of peer tutors assigned to the course
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<PeerTutor> tutors = new ArrayList<>();

        try {
            DataSource ds = new DataSource();
            con = ds.createConnection();
            pstmt = con.prepareStatement("SELECT PeerTutorCourse.PeerTutorID, lastName,firstName FROM PeerTutor join PeerTutorCourse on PeerTutor.PeerTutorID="
                    + " PeerTutorCourse.PeerTutorID where Course_CourseCode=?");
            pstmt.setString(1, courseCode);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PeerTutor turor = new PeerTutor();
                turor.setPeerTutorID(new Integer(rs.getInt("PeerTutorID")));
                turor.setFirstName(rs.getString("firstName"));
                turor.setLastName(rs.getString("lastName"));
                tutors.add(turor);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return tutors;
    }

}
