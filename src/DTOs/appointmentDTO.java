/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.time.LocalDateTime;

/**
 * Transfer Object for appointment data
 * @author eric
 */
public class appointmentDTO {
    
  
    public Integer Appointment_ID;
    private String Title;
    private String Description;
    private String Location;
    private String Type;
    private LocalDateTime Start;
    private LocalDateTime End;
    private LocalDateTime Create_Date;
    private String Created_By;
    private LocalDateTime Last_Update;
    private String Last_Updated_By;
    private int Customer_ID;
    private int User_ID;
    private int Contact_ID;
    
    /**
     * Constructor. Used for holding data for existing appointments.
     * @param Appointment_ID
     * @param Title
     * @param Description
     * @param Location
     * @param Type
     * @param Start
     * @param End
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Customer_ID
     * @param User_ID
     * @param Contact_ID
     */
    public appointmentDTO(
        Integer Appointment_ID,
        String Title,
        String Description,
        String Location,
        String Type,
        LocalDateTime Start,
        LocalDateTime End,
        LocalDateTime Create_Date,
        String Created_By,
        LocalDateTime Last_Update,
        String Last_Updated_By,
        int Customer_ID,
        int User_ID,
        int Contact_ID
    ) {
        this.Appointment_ID = Appointment_ID;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }
    
    /**
     * Constructor. Used for holding data for new appointments prior to updating DB.
     * @param Title
     * @param Description
     * @param Location
     * @param Type
     * @param Start
     * @param End
     * @param Create_Date
     * @param Created_By
     * @param Last_Update
     * @param Last_Updated_By
     * @param Customer_ID
     * @param User_ID
     * @param Contact_ID
     */
    public appointmentDTO(
        String Title,
        String Description,
        String Location,
        String Type,
        LocalDateTime Start,
        LocalDateTime End,
        LocalDateTime Create_Date,
        String Created_By,
        LocalDateTime Last_Update,
        String Last_Updated_By,
        int Customer_ID,
        int User_ID,
        int Contact_ID
    ) {
        this.Appointment_ID = -1;
        this.Title = Title;
        this.Description = Description;
        this.Location = Location;
        this.Type = Type;
        this.Start = Start;
        this.End = End;
        this.Create_Date = Create_Date;
        this.Created_By = Created_By;
        this.Last_Update = Last_Update;
        this.Last_Updated_By = Last_Updated_By;
        this.Customer_ID = Customer_ID;
        this.User_ID = User_ID;
        this.Contact_ID = Contact_ID;
    }
    
    // getters

    /**
     * Appointment_ID getter. 
     * @return appointment Appointment_ID
     */

    public Integer getAppointment_ID() {
        return Appointment_ID;
    }

    /**
     * Title getter. 
     * @return appointment Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * Description getter. 
     * @return appointment description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Location getter. 
     * @return appointment Location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * Type getter. 
     * @return appointment Type
     */
    public String getType() {
        return Type;
    }

    /**
     * Start getter. 
     * @return appointment Start time
     */
    public LocalDateTime getStart() {
        return Start;
    }

    /**
     * End getter. 
     * @return appointment End time
     */
    public LocalDateTime getEnd() {
        return End;
    }

    /**
     * Create_Date getter. 
     * @return appointment creation datetime.
     */
    public LocalDateTime getCreate_Date() {
        return Create_Date;
    }

    /**
     * Created_By getter
     * @return process by which the appointment was created.
     */
    public String getCreated_By() {
        return Created_By;
    }

    /**
     * Last_Update getter. 
     * @return last update datetime for appointment.
     */
    public LocalDateTime getLast_Update() {
        return Last_Update;
    }

    /**
     * Last_Updated_By getter. 
     * @return the method that the appointment was last updated by.
     */
    public String getLast_Updated_By() {
        return Last_Updated_By;
    }

    /**
     * Customer_ID getter. 
     * @return ID of the customer that the appointment is scheduled with.
     */
    public int getCustomer_ID() {
        return Customer_ID;
    }

    /**
     * User_ID getter. 
     * @return the ID of the user that scheduled the appointment.
     */
    public int getUser_ID() {
        return User_ID;
    }

    /**
     * Contact_ID getter. 
     * @return the ID of the contact assigned to the appointment.
     */
    public int getContact_ID() {
        return Contact_ID;
    }
    
    //setters

    /**
     * appointment Title setter. 
     * @param Title
     */

    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * appointment Description setter. 
     * @param Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * appointment Location setter. 
     * @param Location
     */
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /**
     * appointment Type setter. 
     * @param Type
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * appointment Start setter. 
     * @param Start
     */
    public void setStart(LocalDateTime Start) {
        this.Start = Start;
    }

    /**
     * appointment End setter. 
     * @param End
     */
    public void setEnd(LocalDateTime End) {
        this.End = End;
    }

    /**
     * appointment Create_Date setter. 
     * @param Create_Date
     */
    public void setCreate_Date(LocalDateTime Create_Date) {
        this.Create_Date = Create_Date;
    }

    /**
     * appointment Created_By setter. 
     * @param Created_By
     */
    public void setCreated_By(String Created_By) {
        this.Created_By = Created_By;
    }

    /**
     * appointment Last_Update datetime setter. 
     * @param Last_Update
     */
    public void setLast_Update(LocalDateTime Last_Update) {
        this.Last_Update = Last_Update;
    }

    /**
     * appointment Last_Updated setter. 
     * @param Last_Updated_By
     */
    public void setLast_Updated_By(String Last_Updated_By) {
        this.Last_Updated_By = Last_Updated_By;
    }

    /**
     * appointment Customer_ID setter. 
     * @param Customer_ID
     */
    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    /**
     * appointment User_ID setter. 
     * @param User_ID
     */
    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    /**
     * appointment Contact_ID setter. 
     * @param Contact_ID
     */
    public void setContact_ID(int Contact_ID) {
        this.Contact_ID = Contact_ID;
    }
    
    
}
