/** File: PeerTutorDAO.java
 * Author: Lei Luo
 * Date: 2023
 * Description: Demonstration of DAO Design Pattern with Servlet website
 * References:
 */
package dataaccesslayer;

import java.util.List;
import transferobject.PeerTutor;

public interface PeerTutorDAO {

    boolean isPeerTutorRegistered(PeerTutor peerTutor);

    boolean isCourseValid(String courseCode);

    boolean hasPeerTutorTakenCourse(PeerTutor peerTutor, String courseCode);

    String getPeerTutorLetterGradeForCourse(PeerTutor peerTutor, String courseCode);

    boolean isCourseAlreadyAssignedToPeerTutor(PeerTutor peerTutor, String courseCode);

    void assignCourseToPeerTutor(PeerTutor peerTutor, String courseCOde);

    List<PeerTutor> getAllPeerTutorsForCourse(String courseCode);
}
