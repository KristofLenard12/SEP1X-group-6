import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Test extends Application
{
  public void start(Stage window) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getClassLoader().getResource("home.fxml"));
    System.out.println("FXML loaded");
    Scene scene = new Scene(loader.load());
    window.setScene(scene);
    window.show();
  }
}
