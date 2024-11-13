package com.cts.redplastring.redplastringapplication.services;

import com.cts.redplastring.redplastringapplication.request.ClockInRequest;
import com.cts.redplastring.redplastringapplication.request.EmployeeLoginRequest;
import com.cts.redplastring.redplastringapplication.request.UpdateUidRequest;
import com.cts.redplastring.redplastringapplication.response.AbstractResponse;
import com.cts.redplastring.redplastringapplication.response.EmployeeLoginResponse;
import com.cts.redplastring.redplastringapplication.response.JobTypeListResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

public interface EmployeeServices {
    public EmployeeLoginResponse employeeLogin(String lang, EmployeeLoginRequest request) throws Exception;

    public AbstractResponse clockin(String token, Double latitude, Double longitude, ClockInRequest clockInRequest) throws ParseException;

    public AbstractResponse clockout(String token ,Double latitude, Double longitude) throws ParseException;

    public AbstractResponse breakin(String token, Double latitude, Double longitude);

    public AbstractResponse breakout(String token, Double latitude, Double longitude);

    public AbstractResponse travelIn(String token, Double latitude, Double longitude);

    public AbstractResponse travelOut(String token, Double latitude, Double longitude);

    public AbstractResponse getEmployeeState(String token);

    public JobTypeListResponse getJobTypes(String token);

    public AbstractResponse logout(String token, Double latitude, Double longitude);

    public AbstractResponse addUid(String token, UpdateUidRequest request);

    public AbstractResponse checkLocation(String token, Double latitude, Double longitude) throws ParseException;

    public void sendNotification(String to, JSONObject notification) throws JSONException;

}
