import javafx.collections.FXCollections;
import javafx.event

    .ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * @author Marin Bilba, 293141
 * @version 2.0
 * This class is a controller for addUpdateCourse FXML
 */
public class Course_Controller implements EventHandler<ActionEvent>
{
  @FXML private Button homeButton;
  @FXML private Button roomButton;
  @FXML private Button teacherButton;
  @FXML private Button coExaminerButton;
  @FXML private Button courseButton;
  @FXML private Button scheduleButton;
  @FXML private Button settingsButton;
  private CourseList list;

  /**
   * @param actionEvent action
   *                    this method handles the scene switch when the according button is pressed
   */
  @Override public void handle(ActionEvent actionEvent)
  {
    if (actionEvent.getSource() == homeButton)
    {
      try
      {
        changeScene("Home.fxml", actionEvent, list);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }

    if (actionEvent.getSource().equals(roomButton))
    {
      try
      {
        changeScene("Rooms.fxml", actionEvent, list);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }
    if (actionEvent.getSource().equals(teacherButton))
    {
      try
      {
        changeScene("Teacher.fxml", actionEvent, list);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }
    if (actionEvent.getSource().equals(coExaminerButton))
    {
      try
      {
        changeScene("Co-examiner.fxml", actionEvent, list);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }
    if (actionEvent.getSource().equals(courseButton))
    {
      try
      {
        changeScene("addUpdateCourse.fxml", actionEvent, list);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }
    if (actionEvent.getSource().equals(scheduleButton))
    {
      try
      {
        changeScene("addUpdateSchedule.fxml", actionEvent, list);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }
    if (actionEvent.getSource().equals(settingsButton))
    {
      try
      {
        changeScene("Settings.fxml", actionEvent, list);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        System.exit(1);
      }
    }
  }

  /**
   * @param target fxml targer
   * @param event  action event
   * @param list   list of class object
   * @throws IOException throws this exception on error while reading file
   *                     This method handle changing the scene
   */
  private void changeScene(String target, ActionEvent event, Object list)
      throws IOException
  {
    FileAdapter fileHandler = new FileAdapter(null);
    fileHandler.temporaryWrite(courseList, "tempCourse");
    Parent parent = FXMLLoader.load(getClass().getResource(target));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.getScene().setRoot(parent);
    stage.show();
  }

  // Controller settings
  @FXML private ComboBox courses;
  @FXML private Button update;
  @FXML private Button edit;
  @FXML private Button delete;

  @FXML private TextField name;
  @FXML private TextField group;
  @FXML private TextField nrStudents;
  @FXML private TextField semester;

  @FXML private Label nameError;
  @FXML private Label groupError;
  @FXML private Label nrStudentsError;
  @FXML private Label semesterError;

  private boolean updateCheck = false;
  private Course buffer;
  private CourseList courseList;
  private String courseName = null;
  private String groupName = null;
  private int numberOfStudents = 0;
  private int semesterNumber = 0;

  /**
   * @throws IOException            - throws this exception on error while reading file
   * @throws ClassNotFoundException - throws this exception if no such class exists
   *                                method initialize the courseList object, creates an instance of file Adapter
   *                                that is reading from the files and add all objects to the combobox
   */
  public void initialize() throws IOException, ClassNotFoundException
  {
    courseList = new CourseList();

    FileAdapter fileHandler = new FileAdapter(null);
    Object[] objs = fileHandler.temporaryRead("tempCourse");
    for (Object obj : objs)
    {
      System.out.println("a");
      courseList.addCourse((Course) obj);
    }
    courses.setItems(
        FXCollections.observableArrayList(courseList.getAllCourses()));

  }

  /**
   * @param e action event parameter
   *          method handles the "Update" button in the GUI
   */
  public void update(ActionEvent e)
  {
    if (e.getSource() == update)
    {

      //      Name Field Checks
      if (name.getText().length() == 0)
      {
        nameError.setText("Enter the course name");
      }
      else
      {
        nameError.setText("");
        courseName = name.getText();

      }
      //      Group Name Checks
      if (group.getText().length() == 0)
      {
        groupError.setText("Enter the group name");
      }
      else
      {
        groupError.setText("");
        groupName = group.getText();

      }
      //      Number of Students check
      if (nrStudents.getText().length() == 0)
      {
        nrStudentsError.setText("Enter the number of Students");
      }
      else
      {
        nrStudentsError.setText("");
        try
        {
          numberOfStudents = Integer.parseInt(nrStudents.getText());
          //Catches all NumberFormatExceptions but not other errors
        }
        catch (NumberFormatException ex)
        {
          nrStudentsError.setText("Invalid number of students");
        }
        if (numberOfStudents < 0 || numberOfStudents == 0)
        {
          nrStudentsError.setText("Invalid number of students");
        }
      }
      //      Semester Number check
      if (nrStudents.getText().length() == 0)
      {
        semesterError.setText("Enter the semester number");
      }
      else
      {
        semesterError.setText("");
        try
        {
          semesterNumber = Integer.parseInt(semester.getText());
          //Catches all NumberFormatExceptions but not other errors
        }
        catch (NumberFormatException ex)
        {
          semesterError.setText("Invalid semester number");
        }
        if (semesterNumber < 0 || semesterNumber == 0)
        {
          semesterError.setText("Invalid semester number");
        }
      }
      if (nameError.getText().length() == 0
          && semesterError.getText().length() == 0
          && nrStudentsError.getText().length() == 0
          && groupError.getText().length() == 0)
      {
        //      Creating the course object
        buffer = new Course(courseName, groupName, numberOfStudents,
            semesterNumber);
        //      Adding the object to course list
        courseList.addCourse(buffer);

      }

      //      Add items to combobox
      //     Duplicate checker
      if (updateCheck)
      {
        Object obj = courses.getSelectionModel().getSelectedItem();
        if (obj instanceof Course)
        {
          courses.getItems().removeAll(obj);
        }
        updateCheck = false;
      }
      if (!courses.getItems().contains(buffer))
      {
        courses.getItems().add(buffer);
        courses.getSelectionModel().select(courses.getItems().size() - 1);
      }
      //      Set up empty text fields
      name.setText("");
      group.setText("");
      nrStudents.setText("");
      semester.setText("");
    }
  }

  /**
   * @param e action event parameter
   *          method handles the "Edit" button in the GUI
   */
  public void edit(ActionEvent e)
  {
    if (e.getSource() == edit)
    {
      Object obj = courses.getSelectionModel().getSelectedItem();
      if (obj instanceof Course)
      {
        name.setText(((Course) obj).getCourseName());
        group.setText(((Course) obj).getGroup());
        nrStudents.setText(String.valueOf(((Course) obj).getNrStudents()));
        semester.setText(String.valueOf(((Course) obj).getSemester()));
        updateCheck = true;
      }
    }
  }

  /**
   * @param e action event parameter
   *          method handles the "Delete" button in the GUI
   */
  public void delete(ActionEvent e)
  {
    if (e.getSource() == delete)
    {
      Object obj = courses.getSelectionModel().getSelectedItem();
      if (obj instanceof Course)
      {
        courses.getItems().removeAll(obj);
      }
    }
  }
}



