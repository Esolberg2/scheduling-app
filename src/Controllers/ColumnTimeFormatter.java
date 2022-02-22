/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Appointment;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Utility class for changing dateTime format for TableViewColumns within the application. 
 * @author eric
 */
public class ColumnTimeFormatter {
    
    /**
     * Provides callback that can be used by TableColumn function setCellFactory. 
     * @return Callback for use in TableColumn setCellFactory. 
     */
    public static Callback<TableColumn<Appointment, String>, TableCell<Appointment, LocalDateTime>> getFormattedDate() {
        return new Callback<TableColumn<Appointment, String>, TableCell<Appointment, LocalDateTime>>() {

            @Override
            public TableCell<Appointment, LocalDateTime> call(TableColumn<Appointment, String> param) {
                TableCell<Appointment, LocalDateTime> cell = new TableCell<Appointment, LocalDateTime>() {

                    public void updateItem(final LocalDateTime item, boolean empty) {
                        if (item != null) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a");
                            setText(formatter.format(item));
                        }
                    }
                };
                return cell;
            }
        };
    }
    
}
