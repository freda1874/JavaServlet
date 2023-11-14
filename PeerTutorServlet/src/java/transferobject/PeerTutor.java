/** File: PeerTutor.java
 * author: Lei Luo
 * Date: 2023
 * Description: class for PeerTuror and   necessary getters and setters
 */

package transferobject;

/**
 * Represents a peer tutor with necessary attributes and methods to access them.
 * This class provides getters and setters for the peer tutor's ID, first name, and last name.
 * @author: Lei Luo
 * @version 1.0
 * @since JDK 11
 */
public class PeerTutor {

    /**
     * The unique identifier for the peer tutor.
     */
    private int peerTutorID;

    /**
     * The last name of the peer tutor.
     */
    private String lastName;

    /**
     * The first name of the peer tutor.
     */
    private String firstName;
    
    
    
     /**
     * Gets the ID of the peer tutor.
     * 
     * @return The ID of the peer tutor.
     */
       
    public int getPeerTutorID(){
    	return peerTutorID;
    }
    
      /**
     * Sets the ID of the peer tutor.
     * 
     * @param peerTutorID The ID to be set for the peer tutor.
     */
    public void setPeerTutorID(int peerTutorID){
    	this.peerTutorID = peerTutorID;
    }
    
     /**
     * Gets the first name of the peer tutor.
     * 
     * @return The first name of the peer tutor.
     */
    public String getFirstName(){
    	return firstName;
    }
    
    /**
     * Sets the first name of the peer tutor.
     * 
     * @param firstName The first name to be set for the peer tutor.
     */
    public void setFirstName(String firstName){
    	this.firstName = firstName;
    }
    
   /**
     * Gets the last name of the peer tutor.
     * 
     * @return The last name of the peer tutor.
     */
    public String getLastName(){
    	return lastName;
    }
    
    
   /**
     * Sets the last name of the peer tutor.
     * 
     * @param lastName The last name to be set for the peer tutor.
     */
    public void setLastName(String lastName){
    	this.lastName = lastName;
    }
     
}


 