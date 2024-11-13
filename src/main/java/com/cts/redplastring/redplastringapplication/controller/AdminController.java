package com.cts.redplastring.redplastringapplication.controller;

import com.cts.redplastring.redplastringapplication.request.*;
import com.cts.redplastring.redplastringapplication.services.AdminDetailServiceImpl;
import com.cts.redplastring.redplastringapplication.services.AdminService;
import com.cts.redplastring.redplastringapplication.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/admin")
public class AdminController {

     @Autowired
    private AdminService adminDetailsService;

     @Autowired
     private EmployeeServices employeeServices;


       @RequestMapping("/test")
      public String test(){
          return "welcome";
      }

    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) throws Exception{
        return ResponseEntity.ok(adminDetailsService.adminLogin(request));
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestHeader("token") String token,@RequestBody ResetPasswordRequest request) throws Exception{
        return ResponseEntity.ok(adminDetailsService.resetPassword(token,request));
    }

    @GetMapping("/getProfile")
    public ResponseEntity<?> getProfile(@RequestHeader("token") String token) throws Exception{
        return ResponseEntity.ok(adminDetailsService.getProfile(token));
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<?> updateProfile(@RequestHeader("token") String token, @RequestBody AdminDetailsRequest request) throws Exception{
        return ResponseEntity.ok(adminDetailsService.updateProfile(token,request));
    }

    @GetMapping("/getDashboardInfo")
    public ResponseEntity<?> getDashboardInfo(@RequestHeader("token") String token) throws Exception{
        return ResponseEntity.ok(adminDetailsService.getDashboardInfo(token));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("token") String token) throws Exception{
        return ResponseEntity.ok(adminDetailsService.logout(token));
    }

    @GetMapping("/getEmployeeList")
    public ResponseEntity<?> getEmployeeList(@RequestHeader("token") String token) throws Exception{
        return ResponseEntity.ok(adminDetailsService.getEmployeeList(token));
    }

     @PostMapping("/addEmployee")
     public ResponseEntity<?>addEmployee( @RequestHeader("token") String token,@RequestBody EmployeeRequest employeeRequest){
         return ResponseEntity.ok(this.adminDetailsService.addEmployee(token,employeeRequest));
     }


      @PutMapping("/updateEmployeeById/{id}")
     public ResponseEntity<?>updateEmployee( @RequestHeader("token") String token,@PathVariable("id") String id,@RequestBody UpdateEmployeeRequest request){
           return  ResponseEntity.ok(this.adminDetailsService.updateEmployee(token,id,request));
     }

    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<?>updatePassword( @RequestHeader("token") String token,@PathVariable("id") String id,@RequestBody UpdatePasswordRequest request){
        return  ResponseEntity.ok(this.adminDetailsService.updatePassword(token,id,request));
    }


    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?>deleteEmployee(@RequestHeader("token") String token,@PathVariable("id") String id){
        return  ResponseEntity.ok(this.adminDetailsService.deleteEmployee(token,id));
    }


    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<?>getEmployee(@PathVariable("id") String id ,@RequestHeader("token") String token){
        return  ResponseEntity.ok(this.adminDetailsService.getEmployee(id,token));
    }

    @PostMapping("/addTag")
    public ResponseEntity<?>addTags( @RequestHeader("token") String token,@RequestBody TagDetailsRequest request){
        return ResponseEntity.ok(this.adminDetailsService.addTags(token,request));
    }

    @GetMapping("/getTags")
    public ResponseEntity<?>getTags( @RequestHeader("token") String token){
        return ResponseEntity.ok(this.adminDetailsService.getTags(token));
    }

    @PutMapping("/updateTags/{id}")
    public ResponseEntity<?>updateTags(@PathVariable("id") int id, @RequestHeader("token") String token,@RequestBody UpdateTagRequest request){
           return ResponseEntity.ok(this.adminDetailsService.updateTags(id,token,request));
    }

    @DeleteMapping("/deleteTagsById/{id}")
    public ResponseEntity<?>deleteTags(@PathVariable("id") int id ,@RequestHeader("token") String token){
        return  ResponseEntity.ok(this.adminDetailsService.deleteTags(id ,token));
    }

    @GetMapping("/getJobTypes")
    public ResponseEntity<?>getJobTypes(@RequestHeader("token") String token){
        return ResponseEntity.ok(employeeServices.getJobTypes(token));
    }

    @GetMapping("/getJobTypeById/{id}")
    public ResponseEntity<?>getJobTypeById(@RequestHeader("token") String token, @PathVariable("id") Integer id){
        return ResponseEntity.ok(adminDetailsService.getJobTypeById(token, id));
    }

    @GetMapping("/getTagById/{id}")
    public ResponseEntity<?>getTagById(@RequestHeader("token") String token, @PathVariable("id") Integer id){
        return ResponseEntity.ok(adminDetailsService.getTagById(token, id));
    }


    @PostMapping("/addJobType")
    public ResponseEntity<?>addJobType( @RequestHeader("token") String token,@RequestBody EmployeeJobTypeRequest request){
        return ResponseEntity.ok(this.adminDetailsService.addJobType(token,request));
    }

    @DeleteMapping("/deleteJobTypeById/{id}")
    public ResponseEntity<?>deleteJobType(@PathVariable("id") int id ,@RequestHeader("token") String token){
        return  ResponseEntity.ok(this.adminDetailsService.deleteJobType(id ,token));
    }

    @PutMapping("/updateJobTypeById/{id}")
    public ResponseEntity<?>updateJobTypeById(@PathVariable("id") int id ,@RequestHeader("token") String token,@RequestBody EmployeeJobTypeRequest request){
        return  ResponseEntity.ok(this.adminDetailsService.updateJobTypeById(id ,token,request));
    }

    @RequestMapping(value = "/uploadImage",method = RequestMethod.POST)
    public ResponseEntity<?>uploadImages( @RequestHeader("token") String token,@RequestParam("file") MultipartFile file) throws IOException {
           return ResponseEntity.ok(this.adminDetailsService.uploadImages(token,file));
    }

    @PostMapping("/addLocation")
    public ResponseEntity<?>addLocation( @RequestHeader("token") String token,@RequestBody LocationRequest request){
        return ResponseEntity.ok(this.adminDetailsService.addLocation(token,request));
    }

    @GetMapping("/getLocations")
    public ResponseEntity<?> getLocations( @RequestHeader("token") String token){
        return ResponseEntity.ok(this.adminDetailsService.getLocations(token));
    }

    @GetMapping("/getLocationById/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable("id") Integer id, @RequestHeader("token") String token){
        return ResponseEntity.ok(this.adminDetailsService.getLocationById(id, token));
    }

    @PutMapping("/updateLocation/{id}")
    public ResponseEntity<?>updateLocation(@PathVariable("id") int id, @RequestHeader("token") String token,@RequestBody UpdateLocationRequest request){
        return ResponseEntity.ok(this.adminDetailsService.updateLocation(id,token,request));
    }

    @DeleteMapping("/deleteLocation/{id}")
    public ResponseEntity<?>deleteLocation(@PathVariable("id") int id ,@RequestHeader("token") String token){
        return  ResponseEntity.ok(this.adminDetailsService.deleteLocation(id ,token));
    }

    @PostMapping("/getEmployeeTracking")
    public ResponseEntity<?> getEmployeeTracking(@RequestHeader("token") String token, @RequestBody SearchRequest request) throws ParseException {
        return  ResponseEntity.ok(this.adminDetailsService.getEmployeeTracking(token, request));
    }

    @GetMapping("/getEmployeeTrackingById/{id}")
    public ResponseEntity<?>getEmployeeTrackingById(@PathVariable("id") Integer id,@RequestHeader("token") String token) throws ParseException {
        return  ResponseEntity.ok(this.adminDetailsService.getEmployeeTrackingById(id, token));
    }

    @PostMapping("/exportReport")
    public ResponseEntity<ByteArrayResource> exportReport(@RequestHeader("token") String token, @RequestBody ExportRequest request) throws ParseException, IOException {
        return  adminDetailsService.exportReport(token, request);
    }

    @PutMapping("/updateEmployeeTrackingById/{id}")
    public ResponseEntity<?>updateEmployeeTrackingById(@PathVariable("id") Integer id,@RequestHeader("token") String token,@RequestBody UpdateTimetrackingRequest request) throws ParseException {
        return  ResponseEntity.ok(this.adminDetailsService.updateEmployeeTrackingById(id, token, request));
    }



    @RequestMapping(value = "/SignUp", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<?>SignUp(@RequestBody AdminDetailsRequest request){
               return ResponseEntity.ok(this.adminDetailsService.SignUp(request));

    }
//
//    //.........................savsaveEmployeePosition..........................................//
//
//    @RequestMapping(value = "/saveEmployeePosition", produces = "application/json", method = RequestMethod.POST)
//    public ResponseEntity<?>saveEmployeePosition(@RequestBody EmployeePositionRequest employeePositionRequest){
//        EmployeePositions employeePositions = this.employeePositionService.saveEmployeePosition(employeePositionRequest);
//        ApiResponse apiResponse = new ApiResponse();
//         apiResponse.setCode("200");
//         apiResponse.setMessage("employee positions added successfully!"+ employeePositions);
//          return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
//    }
//
////.......................................login...............................................//
//
//    @RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
//    public ResponseEntity<?>AdminLogin(@RequestBody AdminLoginRequest adminLoginRequest){
//        AdminLoginResponse adminLoginResponse = this.adminDetailsService.AdminLogin(adminLoginRequest);
//        return  ResponseEntity.ok(adminLoginResponse);
//    }
}

