package com.cts.redplastring.redplastringapplication.controller;

import com.cts.redplastring.redplastringapplication.request.ClockInRequest;
import com.cts.redplastring.redplastringapplication.request.TimeTrackingRequest;
import com.cts.redplastring.redplastringapplication.request.EmployeeLoginRequest;
import com.cts.redplastring.redplastringapplication.request.UpdateUidRequest;
import com.cts.redplastring.redplastringapplication.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/employee")
public class EmployeeController {

        @Autowired
        private EmployeeServices employeeService;

    @RequestMapping("/test")
    public String test(){
        return "welcome";
    }

    @PostMapping("/login")
    public ResponseEntity<?> employeeLogin(@RequestHeader(value = "lang",required = false) String lang,@RequestBody EmployeeLoginRequest request) throws Exception{
        return ResponseEntity.ok(employeeService.employeeLogin(lang,request));
    }

    @PostMapping("/addUid")
    public ResponseEntity<?> addUid(@RequestHeader("token") String token,@RequestBody UpdateUidRequest request) throws Exception{
        return ResponseEntity.ok(employeeService.addUid(token,request));
    }

    @PostMapping("/clockin")
    public ResponseEntity<?>clockin(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude, @RequestBody ClockInRequest clockInRequest) throws ParseException {
        return ResponseEntity.ok(employeeService.clockin(token, latitude, longitude, clockInRequest));
    }

    @GetMapping("/clockout")
    public ResponseEntity<?>clockout(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude) throws ParseException {
        return ResponseEntity.ok(employeeService.clockout(token ,latitude, longitude));
    }

    @GetMapping("/breakin")
    public ResponseEntity<?>breakin(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude){
        return ResponseEntity.ok(employeeService.breakin(token,latitude, longitude));
    }

    @GetMapping("/checkLocation")
    public ResponseEntity<?>checkLocation(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude) throws ParseException {
        return ResponseEntity.ok(employeeService.checkLocation(token,latitude, longitude));
    }

    @GetMapping("/breakout")
    public ResponseEntity<?>breakout(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude){
        return ResponseEntity.ok(employeeService.breakout(token,latitude, longitude));
    }

    @GetMapping("/travelIn")
    public ResponseEntity<?>travelIn(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude){
        return ResponseEntity.ok(employeeService.travelIn(token,latitude, longitude));
    }

    @GetMapping("/travelOut")
    public ResponseEntity<?>travelOut(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude){
        return ResponseEntity.ok(employeeService.travelOut(token,latitude, longitude));
    }

    @GetMapping("/getEmployeeState")
    public ResponseEntity<?>getEmployeeState(@RequestHeader("token") String token){
        return ResponseEntity.ok(employeeService.getEmployeeState(token));
    }

    @GetMapping("/getJobTypes")
    public ResponseEntity<?>getJobTypes(@RequestHeader("token") String token){
        return ResponseEntity.ok(employeeService.getJobTypes(token));
    }

    @GetMapping("/logout")
    public ResponseEntity<?>logout(@RequestHeader("token") String token,@RequestHeader("latitude") Double latitude,@RequestHeader("longitude") Double longitude){
        return ResponseEntity.ok(employeeService.logout(token,latitude,longitude));
    }

}
