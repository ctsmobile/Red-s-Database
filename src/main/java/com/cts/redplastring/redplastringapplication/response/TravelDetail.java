package com.cts.redplastring.redplastringapplication.response;

import lombok.Data;

@Data
public class TravelDetail {
    private Integer id;
    private String travelInTime;
    private String travelOutTime;
    private String travelInLocation;
    private Integer travelInLocationId;
    private String travelOutLocation;
    private Integer travelOutLocationId;
}
