package lk.ijse.cmjd;

/*
    @author DanujaV
    @created 5/14/23 - 8:52 AM   
*/


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane = FXMLLoader
                .load(this.getClass()
                        .getResource("/view/dashboard_form.fxml"));
        Scene scene = new Scene(pane);

        stage.setScene(scene);
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
