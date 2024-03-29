import java.io.Serializable;

public class Teacher extends Person implements Serializable
{
  /**
   * @author Kristóf Lénárd, 293110
   * @version 1.0
   * This is a class for teachers.
   */

  /**
   * @param ID - The ID of the teacher.
   * @param name - The name of the teacher.
   * This method is a three-argument constructor for teachers.
   */
  public Teacher(String ID, String name)
  {
    super(ID, name);
  }

  @Override public String toString()
  {
    return "Name: " + this.getName() + ", ID: " + this.getID();
  }

  public boolean equals(Object obj)
  {
    if (!(obj instanceof Teacher))
      return false;
    Teacher other = (Teacher) obj;
    return super.equals(other);
  }

}
