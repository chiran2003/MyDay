package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mysql.cj.jdbc.StatementImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AddWindowController {

    @FXML
    private AnchorPane addTasksWindow;

    @FXML
    private DatePicker dateDOT;

    @FXML
    private JFXTextArea txtDescription;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id=txtID.getText();
        String title=txtTitle.getText();
        String description=txtDescription.getText();
        LocalDate dot=dateDOT.getValue();
        // Format the date as a string in the correct SQL format
        String formattedDate = dot != null ? "'" + dot.toString() + "'" : "NULL";

        String SQL = "INSERT INTO Tasks (id, title, description, dot) VALUES ('" + id + "','" + title + "','" + description + "'," + formattedDate + ")";
        System.out.println(SQL);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                Connection connection=java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/MyDay", "root", "Maduranga1231@");
                Statement stm=connection.createStatement();
                stm.executeUpdate(SQL);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {

    }

}
