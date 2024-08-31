package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HomeWindowController implements Initializable {

    @FXML
    private ListView<CheckBox> listTodo;

    @FXML
    void btnAccountOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/account_window.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/add_window.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadTodoList() {
        String SQL = "Select * from tasks";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Connection connection=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDay", "root", "Maduranga1231@");
                Statement stm=connection.createStatement();
                try (ResultSet rst = stm.executeQuery(SQL)) {
                    ObservableList<CheckBox> tasks = FXCollections.observableArrayList();
                    while (rst.next()) {
                        String id = rst.getString("id"); //rst.getString(1);
                        String title = rst.getString("title");
                        String description = rst.getString("description");

                        java.sql.Date sqlDate = rst.getDate("dot");
                        LocalDate dot = sqlDate != null ? sqlDate.toLocalDate() : null;

                        CheckBox checkBox = new CheckBox(title);

                        checkBox.setUserData(id);

                        tasks.add(checkBox);
                    }
                    listTodo.setItems(tasks);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTodoList();
    }
}
