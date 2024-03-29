import java.io.Serializable;

public class Room implements Serializable
{
  /**
   * @author Ali Bahrani, 294418
   * @version 1.0.0.4
   * this class is responsible for managing room data
   */
  private int numberOfSeats;
  private String roomNumber;
  private byte projector;

  /**
   * no argument constructor
   * initializing projector state, number of seats and room number
   */
  public Room()
  {
    projector = 5;
    numberOfSeats = -1;
    roomNumber = null;
  }

  /**
   * a three argument constructor for all fields
   *
   * @param numberOfSeats - the number of seats in the room
   * @param roomNumber    - the room identifying number
   * @param projector     - the type of projector inside the room
   */
  public Room(String roomNumber, int numberOfSeats, byte projector)
  {
    this.roomNumber = roomNumber;
    this.projector = projector;
    this.numberOfSeats = numberOfSeats;
  }

  /**
   * @return projector state
   * return the projector state of the room, 0 for non, 1 for VGA, 2 for hdmi and 3 for both, 5 for null
   */
  public byte getProjector()
  {
    return projector;
  }

  /**
   * @return Number of seats in the class
   * returns the number of the seats in the class, -1 is not defined
   */
  public int getNumberOfSeats()
  {
    return numberOfSeats;
  }

  /**
   * @return the room number
   * returns the room number
   */
  public String getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * @param numberOfSeats sets the number of seats in the room
   */
  public void setNumberOfSeats(int numberOfSeats)
  {
    this.numberOfSeats = numberOfSeats;
  }

  /**
   * @param projector sets the state of projector for the room, 0 for non, 1 for VGA, 2 for hdmi and 3 for both
   */
  public void setProjector(byte projector)
  {
    this.projector = projector;
  }

  /**
   * @param roomNumber o the room
   *                   sets the room number
   */
  public void setRoomNumber(String roomNumber)
  {
    this.roomNumber = roomNumber;
  }

  /**
   * @param object of room type
   *               checks if the rooms are the same
   */
  public boolean equals(Object object)
  {
    if (!(object instanceof Room))
    {
      return false;
    }
    Room tmp = (Room) object;
    if ((numberOfSeats == tmp.getNumberOfSeats()) && (roomNumber
        .equalsIgnoreCase(tmp.roomNumber)) && (projector == tmp.getProjector()))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  public String toString()
  {
    String ret = "";
    ret =
        "Room: " + getRoomNumber() + ", Number of seats: " + getNumberOfSeats();
    if (getProjector() == (byte) 0)
    {
      ret += ", Projector: none";
    }
    else if (getProjector() == (byte) 1)
    {
      ret += ", Projector: VGA only";
    }
    else if (getProjector() == (byte) 2)
    {
      ret += ", Projector: HDMI only";
    }
    else if (getProjector() == (byte) 3)
    {
      ret += ", Projector: VGA and HDMI";
    }
    return ret;
  }
}

