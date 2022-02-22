/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * TimeConverter Class. 
 * @author eric
 */
public class TimeConverter {
    
    /**
     * Convert UTC time to LocalTime. 
     * @param utcTime
     * @return
     */
    public static LocalDateTime toLocal(LocalDateTime utcTime) {        
        ZoneId localZone = TimeZone.getDefault().toZoneId();
        ZoneId utcZone = ZoneId.of("UTC");

        LocalDateTime localDateTime = utcTime.atZone(utcZone)
                                               .withZoneSameInstant(localZone)
                                               .toLocalDateTime();
        return localDateTime;
    }
    
    /**
     * Convert LocalTime to UTC time. 
     * @param localTime
     * @return
     */
    public static LocalDateTime toUTC(LocalDateTime localTime) {        
        ZoneId defaultZone = TimeZone.getDefault().toZoneId();
        ZoneId utcZone = ZoneId.of("UTC");

        LocalDateTime utcDateTime = localTime.atZone(defaultZone)
                                               .withZoneSameInstant(utcZone)
                                               .toLocalDateTime();
        return utcDateTime;
    }
}
