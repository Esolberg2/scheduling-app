/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

/**
 * Custom class to hold data for ContatSchedule view TableView. 
 * @author eric
 */
public class Quartet {
    private String year;
    private String month;
    private String type;
    private String count;
    
    /**
     * Constructor
     * @param first
     * @param second
     * @param third
     * @param fourth
     */
    public Quartet(String first, String second, String third, String fourth) {
        this.year = first;
        this.month = second;
        this.type = third;
        this.count = fourth;
    }

    /**
     * year Getter. 
     * @return
     */
    public String getYear() {
        return year;
    }

    /**
     * month Getter. 
     * @return
     */
    public String getMonth() {
        return month;
    }

    /**
     * type Getter. 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * count Getter. 
     * @return
     */
    public String getCount() {
        return count;
    }

    
    
}
