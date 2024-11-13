package com.cts.redplastring.redplastringapplication.services;

import com.cts.redplastring.redplastringapplication.constant.AppConstant;
import com.cts.redplastring.redplastringapplication.constant.ValidationExceptionFEMessageConstant;
import com.cts.redplastring.redplastringapplication.enums.EmployeeState;
import com.cts.redplastring.redplastringapplication.exception.ValidationException;
import com.cts.redplastring.redplastringapplication.model.*;
import com.cts.redplastring.redplastringapplication.repository.*;
import com.cts.redplastring.redplastringapplication.request.*;
import com.cts.redplastring.redplastringapplication.response.*;
import com.cts.redplastring.redplastringapplication.util.AppUtility;
import com.cts.redplastring.redplastringapplication.util.TokenGenerator;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class AdminDetailServiceImpl implements AdminService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
   private PasswordEncoder passwordEncoder;

    @Autowired
   private TagDetailsRepository tagDetailsRepository;

    @Autowired
    private EmployeeJobTypeRepository employeeJobTypeRepository;

     @Autowired
     private TokenGenerator generator;

     @Autowired
     private LocationRepository locationRepository;

     @Autowired
     private AdminRepository adminRepository;

     @Autowired
     private TimeTrackingRepository timeTrackingRepository;

     @Autowired
     private TravelDetailRepository travelDetailRepository;

     @Autowired
     private ApiLogRepository apiLogRepository;

     @Autowired
     private EmployeeServices employeeServices;

    @Value("${upload.path}")
    private String path;

    public LoginResponse adminLogin(LoginRequest request) throws Exception {
        LoginResponse response = new LoginResponse();
        AdminDetails admin = adminRepository.findByEmail(request.getEmail());
        // Employee employee = employeeRepository.findByEmailAndUserType(request.getEmail(), userType.ADMIN.toString());
        if (admin == null) {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.ADMIN_NOT_FOUND);
        }else {

          if(passwordEncoder.matches(request.getPassword(), admin.getPassword())){
             String token = generator.generateNewToken(admin.getUserId());
                 response.setToken(token);
                 response.setName(admin.getName());
                 response.setProfilePhoto(admin.getProfilePhoto());
                 response.setMessage(AppConstant.SUCCESS);
                 response.setFrontendMessage(AppConstant.ADMIN_LOGGEDIN);
          } else {
              throw new ValidationException(AppConstant.FAILURE,
                      ValidationExceptionFEMessageConstant.LOGIN_ERROR_MESSAGE);
          }
        }
      return response;
    }

    public AbstractResponse addEmployee(String token ,EmployeeRequest employeeRequest) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
      //  Employee employee = employeeRepository.findByUserId(userId);
            Employee employee = new Employee();
            employee.setEmployeeId(AppUtility.generateEmployeeId());
            employee.setNotes(employeeRequest.getNotes());
            employee.setUserId(AppUtility.generateUserId());
            System.out.println(passwordEncoder.encode(employeeRequest.getPassword()));
            employee.setPassword(passwordEncoder.encode(employeeRequest.getPassword()));
            employee.setEmail(employeeRequest.getEmail());
            employee.setEmployeeName(employeeRequest.getFirstName() + " " +employeeRequest.getLastName());
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setPhoneNo(employeeRequest.getPhoneNo());
            employee.setFcmKey(employeeRequest.getFcmKey());
            employee.setForeman(employeeRequest.getForeman());
            employee.setPosition(employeeRequest.getPosition());
            employee.setTags(employeeRequest.getTags());
            employee.setUsername(employeeRequest.getUsername());
            employee.setRateOfPay(employeeRequest.getRateOfPay());
            employee.setCreatedBy(employeeRequest.getCreatedBy());
            employeeRepository.save(employee);
            response.setMessage(AppConstant.SUCCESS);
            response.setFrontendMessage(AppConstant.EMPLOYEE_ADDED);
            return response;
        }

    public AbstractResponse updateEmployee(String token,String id,UpdateEmployeeRequest request) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
        //Employee employee = employeeRepository.findByUserId(userId);
        Employee emp = employeeRepository.findByEmployeeId(id);
        if (emp == null) {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
        if (emp != null) {
            emp.setFirstName(request.getFirstName());
            emp.setLastName(request.getLastName());
            emp.setEmployeeName(request.getFirstName()+" "+request.getLastName());
            emp.setEmail(request.getEmail());
            emp.setPhoneNo(request.getPhoneNo());
            emp.setNotes(request.getNotes());
            // employee.setEmployeeId(updateEmployeeRequest.getEmployeeId());
            emp.setPosition(request.getPosition());
            emp.setForeman(request.getForeman());
            emp.setRateOfPay(request.getRateOfPay());
            //emp.setPassword(passwordEncoder.encode(request.getPassword()));
            emp.setTags(request.getTags());
            if(request.getDeviceId() == null){
                tokenService.deleteTokensForUserId(emp.getUserId());
            }
            emp.setDeviceId(request.getDeviceId());
            employeeRepository.save(emp);
            response.setMessage(AppConstant.SUCCESS);
            response.setFrontendMessage(AppConstant.EMPLOYEE_UPDATED);
            return response;
        } else {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
    }

    public AbstractResponse deleteEmployee(String token, String id) {
        AbstractResponse response = new AbstractResponse();
           tokenService.getUserIdByToken(token);
            Employee employee = employeeRepository.findByEmployeeId(id);
            employee.setStatus("deleted");
            employee.setDeviceId(null);
            employee.setEmployeeState(null);
            employeeRepository.save(employee);
            tokenService.deleteTokensForUserId(employee.getUserId());
            List<TimeTracking> timeTrackingList = timeTrackingRepository.findByEmployeeId(id);
            timeTrackingRepository.deleteAll(timeTrackingList);
            List<TravelDetails> travelDetails = travelDetailRepository.findByEmployeeId(id);
            travelDetailRepository.deleteAll(travelDetails);
            List<ApiLog> logs = apiLogRepository.findByEmployeeId(id);
            apiLogRepository.deleteAll(logs);
            response.setMessage(AppConstant.SUCCESS);
            response.setFrontendMessage(AppConstant.EMPLOYEE_REMOVED);
            return response;
        }

        @Override
    public GetEmployeeResponse getEmployee(String id, String token) {
        GetEmployeeResponse response = new GetEmployeeResponse();
        tokenService.getUserIdByToken(token);
        Employee emp = employeeRepository.findByEmployeeId(id);
        if(emp==null){
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
        response.setEmployeeName(emp.getEmployeeName());
        response.setEmployeeState(emp.getEmployeeState());
        response.setEmail(emp.getEmail());
        response.setPhoneNo(emp.getPhoneNo());
        response.setEmployeeId(emp.getEmployeeId());
        response.setDeviceId(emp.getDeviceId());
        response.setPosition(emp.getPosition());
        response.setForeman(emp.getForeman());
        response.setRateOfPay(emp.getRateOfPay());
        response.setUsername(emp.getUsername());
        response.setTags(emp.getTags());
        response.setNotes(emp.getNotes());
        response.setMessage(AppConstant.SUCCESS);
        response.setFrontendMessage(AppConstant.EMPLOYEE_FETCHED);
        return response;
    }

    @Override
    public AbstractResponse  addTags(String token, TagDetailsRequest request) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
            TagDetails details = new TagDetails();
              details.setTagName(request.getTagName());
              details.setTask(request.getTask());
              details.setCreatedBy(request.getCreatedBy());
              tagDetailsRepository.save(details);
              response.setMessage(AppConstant.SUCCESS);
              response.setFrontendMessage(AppConstant.TAGS_ADDED);
              return response;
        }

    @Override
    public AbstractResponse addJobType(String token, EmployeeJobTypeRequest request) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
            EmployeeJobType jobType = new EmployeeJobType();
              jobType.setJobName(request.getJobName());
              jobType.setImageName(request.getImageName());
              employeeJobTypeRepository.save(jobType);
              response.setMessage(AppConstant.SUCCESS);
              response.setFrontendMessage(AppConstant.EMPLOYEE_JOB_TYPE);
            return response;
        }

    @Override
    public AbstractResponse updateTags(int id,String token, UpdateTagRequest request) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
        Optional<TagDetails> tag = tagDetailsRepository.findById(id);
        TagDetails tagDetails = tag.get();
        if (tagDetails == null) {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }

        if (tagDetails != null){
            tagDetails.setTagName(request.getTagName());
            tagDetails.setTask(request.getTask());
            tagDetailsRepository.save(tagDetails);
            response.setMessage(AppConstant.SUCCESS);
            response.setFrontendMessage(AppConstant.TAGS_UPDATED);
            return response;
        }else {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }

    }

    @Override
    public AbstractResponse deleteTags(int id,String token) {
        AbstractResponse response = new AbstractResponse();
        tokenService.getUserIdByToken(token);
        Optional<TagDetails> tag = tagDetailsRepository.findById(id);
        tagDetailsRepository.delete(tag.get());
        response.setMessage(AppConstant.SUCCESS);
        response.setFrontendMessage(AppConstant.TAG_REMOVED);
        return response;

    }

    @Override
    public AbstractResponse deleteJobType(int id, String token) {
        AbstractResponse response = new AbstractResponse();
        tokenService.getUserIdByToken(token);
        Optional<EmployeeJobType> jobType = employeeJobTypeRepository.findById(id);
        employeeJobTypeRepository.delete(jobType.get());
        response.setMessage(AppConstant.SUCCESS);
        response.setFrontendMessage(AppConstant.JOB_TYPE);
        return response;
    }

    @Override
    public AbstractResponse uploadImages(String token, MultipartFile file) throws IOException {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
            String filename = file.getOriginalFilename();
            String randomID = UUID.randomUUID().toString();
            String fileName = randomID.concat(filename.substring(filename.lastIndexOf(".")));
            String filePath = path + File.separator + fileName;
            File f = new File(path);
            if(!f.exists()){
                f.mkdir();
            }
            Files.copy(file.getInputStream(), Paths.get(filePath));
                 response.setMessage(fileName);
                 response.setFrontendMessage(AppConstant.IMAGE_UPLOADED);
            return response;

    }

    @Override
    public AbstractResponse addLocation(String token, LocationRequest request) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
            LocationInfo location = new LocationInfo();
              location.setAddress(request.getAddress());
              location.setRangeVale(request.getRangeVale());
              location.setJobSide(request.getJobSide());
              location.setLongitude(request.getLongitude());
              location.setLatitude(request.getLatitude());
              location.setShiftStartTime(request.getShiftStartTime());
              location.setShiftEndTime(request.getShiftEndTime());
              location.setProjectNumber(request.getProjectNumber());
              location.setProjectName(request.getProjectName());
              locationRepository.save(location);
               response.setMessage(AppConstant.SUCCESS);
               response.setFrontendMessage(AppConstant.LOCATION_ADDED);
            return response;
        }

    @Override
    public AbstractResponse updateLocation(int id, String token, UpdateLocationRequest request) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
        Optional<LocationInfo> location = locationRepository.findById(id);
        LocationInfo locations = location.get();
        if (locations == null) {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
        if (locations != null){
                locations.setJobSide(request.getJobSide());
                locations.setAddress(request.getAddress());
                locations.setProjectNumber(request.getProjectNumber());
                locations.setProjectName(request.getProjectName());
                locations.setLatitude(request.getLatitude());
                locations.setLongitude(request.getLongitude());
                locations.setRangeVale(request.getRangeVale());
                locations.setShiftStartTime(request.getShiftStartTime());
                locations.setShiftEndTime(request.getShiftEndTime());
                locationRepository.save(locations);
              response.setMessage(AppConstant.SUCCESS);
              response.setFrontendMessage(AppConstant.LOCATION_UPDATED);
              return response;
        }else {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }


    }

    @Override
    public AbstractResponse deleteLocation(int id, String token) {
        AbstractResponse response = new AbstractResponse();
        tokenService.getUserIdByToken(token);
        Optional<LocationInfo> location = locationRepository.findById(id);
        location.get().setDeleted(true);
        locationRepository.save(location.get());
        response.setMessage(AppConstant.SUCCESS);
        response.setFrontendMessage(AppConstant.LOCATION_REMOVED);
        return response;

    }

    @Override
    public AbstractResponse SignUp(AdminDetailsRequest request) {
        AbstractResponse response = new AbstractResponse();
        AdminDetails details = new AdminDetails();
          details.setUserId(AppUtility.generateUserId());
          details.setName(request.getName());
          details.setEmail(request.getEmail());
          details.setProfilePhoto(request.getProfilePhoto());
          details.setPassword(passwordEncoder.encode(request.getPassword()));
          adminRepository.save(details);
          response.setMessage(AppConstant.SUCCESS);
          response.setFrontendMessage(AppConstant.ADMIN_SIGNUP);
        return response;
    }

    @Override
    public AbstractResponse getDashboardInfo(String token) {
        String userId =  tokenService.getUserIdByToken(token);
        AdminDetails adminDetails = adminRepository.findByUserId(userId);
        AdminDashboardResponse response = new AdminDashboardResponse();
        response.setName(adminDetails.getName());
        response.setTotalEmployee(employeeRepository.findByStatus("registered", Sort.by(Sort.Direction.DESC, "createdDate")).size());
        response.setTotalEmployeeClockedIn(employeeRepository.findByEmployeeStateNotAndStatus(EmployeeState.checkedOut.toString(),"registered").size());
        response.setTotalEmployeeOnBreak(employeeRepository.findByEmployeeStateAndStatus(EmployeeState.breakIn.toString(),"registered").size());
        response.setTotalEmployeeOnTravel(employeeRepository.findByEmployeeStateAndStatus(EmployeeState.travelIn.toString(),"registered").size());
        response.setMessage(AppConstant.SUCCESS);
        return response;
    }

    @Override
    public AbstractResponse logout(String token) {
        tokenService.getUserIdByToken(token);
        tokenService.deleteToken(token);
        AbstractResponse abstractResponse = new AbstractResponse();
        abstractResponse.setMessage(AppConstant.SUCCESS);
        abstractResponse.setFrontendMessage(AppConstant.LOGOUT_MESSAGE);
        return abstractResponse;
    }

    @Override
    public EmployeeListResponse getEmployeeList(String token) {
        tokenService.getUserIdByToken(token);
        EmployeeListResponse response = new EmployeeListResponse();
        response.setMessage(AppConstant.SUCCESS);
        List<Employee> employeesList = employeeRepository.findByStatus("registered", Sort.by(Sort.Direction.DESC, "createdDate"));
        List<GetEmployeeResponse> list = new ArrayList<GetEmployeeResponse>();
        for(Employee employee:employeesList){
            GetEmployeeResponse getEmployeeResponse = new GetEmployeeResponse();
            getEmployeeResponse.setEmployeeId(employee.getEmployeeId());
            getEmployeeResponse.setEmployeeName(employee.getEmployeeName());
            getEmployeeResponse.setEmail(employee.getEmail());
            getEmployeeResponse.setForeman(employee.getForeman());
            getEmployeeResponse.setTags(employee.getTags());
            getEmployeeResponse.setPosition(employee.getPosition());
            getEmployeeResponse.setUsername(employee.getUsername());
            getEmployeeResponse.setDeviceId(employee.getDeviceId());
            getEmployeeResponse.setPhoneNo(employee.getPhoneNo());
            getEmployeeResponse.setUserId(employee.getUserId());
            getEmployeeResponse.setRateOfPay(employee.getRateOfPay());
            getEmployeeResponse.setEmployeeState(employee.getEmployeeState());
            list.add(getEmployeeResponse);
        }
        response.setList(list);
        return response;
    }

    public AbstractResponse resetPassword(String token, ResetPasswordRequest request) {
        String userId = tokenService.getUserIdByToken(token);
        AdminDetails adminDetails = adminRepository.findByUserId(userId);
        AbstractResponse response = new AbstractResponse();
        if (adminDetails == null) {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.ADMIN_NOT_FOUND);
        }else {
            if (passwordEncoder.matches(request.getOldPassword(), adminDetails.getPassword())) {
                adminDetails.setPassword(passwordEncoder.encode(request.getNewPassword()));
                adminRepository.save(adminDetails);
                response.setMessage(AppConstant.SUCCESS);
                return response;
            }
            else{
                throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.WRONG_PASSWORD);
            }
        }
    }

    @Override
    public TagDetailList getTags(String token) {
        tokenService.getUserIdByToken(token);
        TagDetailList response = new TagDetailList();
        response.setMessage(AppConstant.SUCCESS);
        List<TagDetails> tags = tagDetailsRepository.findAll();
        List<TagDetail> tagDetailList = new ArrayList<TagDetail>();
        for(TagDetails tag : tags){
            TagDetail tagDetail = new TagDetail();
            tagDetail.setId(tag.getId());
            tagDetail.setTagName(tag.getTagName());
            tagDetail.setTask(tag.getTask());
            tagDetailList.add(tagDetail);
        }
        response.setTagDetailList(tagDetailList);
        return response;
    }

    @Override
    public AbstractResponse getJobTypeById(String token, Integer id) {
        tokenService.getUserIdByToken(token);
        String FILE_URL = "http://54.82.200.45/Redplastering/uploads/";
        Optional<EmployeeJobType> jobType = employeeJobTypeRepository.findById(id);
        if(jobType.get() == null){
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
        JobTypeDetail jobTypeDetail = new JobTypeDetail();
        jobTypeDetail.setJobId(jobType.get().getId());
        jobTypeDetail.setJobName(jobType.get().getJobName());
        jobTypeDetail.setImageName(FILE_URL+jobType.get().getImageName());
        jobTypeDetail.setMessage(AppConstant.SUCCESS);
        return jobTypeDetail;
    }

    @Override
    public AbstractResponse getTagById(String token, Integer id) {
        tokenService.getUserIdByToken(token);
        Optional<TagDetails> tag = tagDetailsRepository.findById(id);
        TagDetails tagDetails = tag.get();
        if (tagDetails == null) {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
        TagDetail tagDetail = new TagDetail();
        tagDetail.setId(tagDetails.getId());
        tagDetail.setTask(tagDetails.getTask());
        tagDetail.setTagName(tagDetails.getTagName());
        tagDetail.setMessage(AppConstant.SUCCESS);
        return tagDetail;
    }

    @Override
    public LocationListResponse getLocations(String token) {
        tokenService.getUserIdByToken(token);
        List<LocationInfo> locationInfoList = locationRepository.findByDeleted(false);
        List<LocationDetail> locationDetailList = new ArrayList<LocationDetail>();
        for(LocationInfo locationInfo: locationInfoList){
            LocationDetail locationDetail = new LocationDetail();
            locationDetail.setId(locationInfo.getId());
            locationDetail.setLongitude(locationInfo.getLongitude());
            locationDetail.setLatitude(locationInfo.getLatitude());
            locationDetail.setAddress(locationInfo.getAddress());
            locationDetail.setJobSide(locationInfo.getJobSide());
            locationDetail.setRangeVale(locationInfo.getRangeVale());
            locationDetail.setShiftStartTime(locationInfo.getShiftStartTime());
            locationDetail.setShiftEndTime(locationInfo.getShiftEndTime());
            locationDetail.setProjectNumber(locationInfo.getProjectNumber());
            locationDetail.setProjectName(locationInfo.getProjectName());
            locationDetailList.add(locationDetail);
        }
        LocationListResponse response = new LocationListResponse();
        response.setLocationDetailList(locationDetailList);
        response.setMessage(AppConstant.SUCCESS);
        return response;
    }

    @Override
    public AbstractResponse getLocationById(Integer id, String token) {
        tokenService.getUserIdByToken(token);
        Optional<LocationInfo> locationInfo = locationRepository.findById(id);
        LocationInfo location = locationInfo.get();
        if(location == null){
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
        LocationDetail locationDetail = new LocationDetail();
        locationDetail.setId(location.getId());
        locationDetail.setLongitude(location.getLongitude());
        locationDetail.setLatitude(location.getLatitude());
        locationDetail.setJobSide(location.getJobSide());
        locationDetail.setRangeVale(location.getRangeVale());
        locationDetail.setAddress(location.getAddress());
        locationDetail.setShiftStartTime(location.getShiftStartTime());
        locationDetail.setShiftEndTime(location.getShiftEndTime());
        locationDetail.setProjectNumber(location.getProjectNumber());
        locationDetail.setProjectName(location.getProjectName());
        return locationDetail;
    }

    @Override
    public AbstractResponse getEmployeeTracking(String token, SearchRequest request) throws ParseException {
        TimeTrackingListResponse response = new TimeTrackingListResponse();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = "";
        if(request.getDate() == null){
            date = format.format(new Date());
        }
        else{
            date = request.getDate();
        }
        List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
        boolean job = false;
        boolean employeeFilter = false;
        boolean tag = false;
        boolean location = false;
        if(request.getJobId()!=null&&request.getJobId()!=0){
            job = true;
        }
        if(request.getTag()!=null && !request.getTag().equals("all")){
            tag = true;
        }
        if(request.getEmployeeId()!=null&&!request.getEmployeeId().equals("all")){
            employeeFilter = true;
        }
        if(request.getLocationId()!=null&&request.getLocationId()!=0){
            location = true;
        }
        if(job){
            if(employeeFilter){
                if(location){
                    timeTrackingList = timeTrackingRepository.findByEmployeeIdAndJobIdAndClockInLocationAndCreatedDateContaining(request.getEmployeeId(),request.getJobId(),request.getLocationId(),date);
                }else{
                    timeTrackingList = timeTrackingRepository.findByEmployeeIdAndJobIdAndCreatedDateContaining(request.getEmployeeId(),request.getJobId(),date);
                }
            }else{
                if(location){
                    timeTrackingList = timeTrackingRepository.findByJobIdAndClockInLocationAndCreatedDateContaining(request.getJobId(),request.getLocationId(),date);
                }else{
                    timeTrackingList = timeTrackingRepository.findByJobIdAndCreatedDateContaining(request.getJobId(),date);
                }
            }
        }else{
            if(employeeFilter){
                if(location){
                    timeTrackingList = timeTrackingRepository.findByEmployeeIdAndClockInLocationAndCreatedDateContaining(request.getEmployeeId(),request.getLocationId(),date);
                }else{
                    timeTrackingList.add(timeTrackingRepository.findByEmployeeIdAndCreatedDateContaining(request.getEmployeeId(),date));
                }
            }else{
                if(location){
                    timeTrackingList = timeTrackingRepository.findByClockInLocationAndCreatedDateContaining(request.getLocationId(),date);
                }else{
                    timeTrackingList = timeTrackingRepository.findByCreatedDateContaining(date);
                }
            }
        }

        List<TimeTrackingResponse> list = new ArrayList<>();
        for (TimeTracking timeTracking: timeTrackingList){
            TimeTrackingResponse timeTrackingResponse = new TimeTrackingResponse();
            timeTrackingResponse.setId(timeTracking.getId());
            System.out.println("Employee id: "+ timeTracking.getEmployeeId());
            Employee employee = employeeRepository.findByEmployeeId(timeTracking.getEmployeeId());
            timeTrackingResponse.setEmployeeName(employee.getEmployeeName());
            timeTrackingResponse.setTag(employee.getTags());
            timeTrackingResponse.setJobType(employeeJobTypeRepository.findById(timeTracking.getJobId()).get().getJobName());
            SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            timeTrackingResponse.setClockInTime(formatTime.format(sdf1.parse(timeTracking.getClockIn())));
            String hours = "";
            long workingHours = 0;
            long calculateTravelHours = 0;
            long calculateBreakHours = 0;
            long breakHours = 0;
            long travelHours = 0;
            if(timeTracking.getBreakInLocation()==null){
                timeTrackingResponse.setBreakInTime("");
            }else{
            timeTrackingResponse.setBreakInTime(formatTime.format(sdf1.parse(timeTracking.getBreakIn())));}
            if(timeTracking.getBreakOutLocation()==null) {
                timeTrackingResponse.setBreakOutTime(formatTime.format(new Date()));
                timeTrackingResponse.setBreakHours("0 hours");
            }else{
            timeTrackingResponse.setBreakOutTime(formatTime.format(sdf1.parse(timeTracking.getBreakOut())));
            breakHours = hoursDifference(sdf1.parse(timeTracking.getBreakIn()),sdf1.parse(timeTracking.getBreakOut()));
            calculateBreakHours = breakHours;
            timeTrackingResponse.setBreakOutLocation(locationRepository.findById(timeTracking.getBreakOutLocation()).get().getJobSide());
            if(breakHours<0){
                    breakHours = - breakHours;
            }
            if(breakHours<=30){
                timeTrackingResponse.setBreakHours("30 minutes");
            }
            else{
                if(breakHours<60){
                    timeTrackingResponse.setBreakHours(breakHours + " minutes");
                }
                else{
                    breakHours = breakHours/60;
                    timeTrackingResponse.setBreakHours(breakHours+" hours");
                }
            }
            }
            List<TravelDetail> travelDetailList = new ArrayList<TravelDetail>();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            List<TravelDetails> travelDetails = travelDetailRepository.findByEmployeeIdAndCreatedDateContaining(timeTracking.getEmployeeId(),sdf2.format(sdf1.parse(timeTracking.getCreatedDate())));
            for(TravelDetails travelDetails1:travelDetails){
                TravelDetail travelDetail = new TravelDetail();
                travelDetail.setTravelInTime(formatTime.format(sdf1.parse(travelDetails1.getTravelIn())));
                travelDetail.setTravelInLocation(locationRepository.findById(travelDetails1.getTravelInLocations()).get().getJobSide());
                if(travelDetails1.getTravelOut() == null){
                    travelDetail.setTravelOutTime(formatTime.format(new Date()));
                    travelDetail.setTravelOutLocation("");
                }
                else{
                    travelHours = travelHours + hoursDifference(sdf1.parse(travelDetails1.getTravelIn()),sdf1.parse(travelDetails1.getTravelOut()));
                    travelDetail.setTravelOutTime(formatTime.format(sdf1.parse(travelDetails1.getTravelOut())));
                    travelDetail.setTravelOutLocation(locationRepository.findById(travelDetails1.getTravelOutLocations()).get().getJobSide());
                }
                travelDetailList.add(travelDetail);
            }
            timeTrackingResponse.setTravelDetailList(travelDetailList);
            calculateTravelHours = travelHours;
            if(timeTracking.getClockOut()==null || timeTracking.getEmployeeState().equals(EmployeeState.temporary_clockout.toString())){
                timeTrackingResponse.setClockOutTime(formatTime.format(new Date()));
                timeTrackingResponse.setWorkingHours("0 hours");
            }
            else {
                System.out.println("Error occured here : "+timeTracking.getId());
                timeTrackingResponse.setClockOutTime(formatTime.format(sdf1.parse(timeTracking.getClockOut())));
                timeTrackingResponse.setClockOutLocation(locationRepository.findById(timeTracking.getClockOutLocation()).get().getJobSide());
                if(calculateBreakHours<0){
                    calculateBreakHours = - calculateBreakHours;
                }
                if(calculateTravelHours<0){
                    calculateTravelHours = -calculateTravelHours;
                }
                workingHours = hoursDifference(sdf1.parse(timeTracking.getClockIn()),sdf1.parse(timeTracking.getClockOut()))-calculateBreakHours-calculateTravelHours;
                if(workingHours<0){
                    workingHours = - workingHours;
                }
                if(workingHours<60) {
                    timeTrackingResponse.setWorkingHours(workingHours + " minutes");
                }
                else{
                    workingHours = workingHours/60;
                    timeTrackingResponse.setWorkingHours(workingHours + " hours");
                }
            }
            if(travelHours<0){
                travelHours = - travelHours;
            }
            if(travelHours<60){
                timeTrackingResponse.setTravelHours(travelHours + " minutes");
            }
            else{

                travelHours = travelHours/60;
                timeTrackingResponse.setTravelHours(travelHours+" hours");
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            timeTrackingResponse.setCurrentDate(sdf.format(sdf1.parse(timeTracking.getCreatedDate())));
            if(tag && request.getTag().equals(employee.getTags())) {
                list.add(timeTrackingResponse);
            }
            if(!tag){
                list.add(timeTrackingResponse);
            }
        }
        response.setTimeTrackingList(list);
        response.setEmployeeList(getEmployeeList(token).getList());
        response.setTagDetailList(getTags(token).getTagDetailList());
        response.setLocationDetailList(getLocations(token).getLocationDetailList());
        response.setJobTypeList(employeeServices.getJobTypes(token).getData());
        response.setMessage(AppConstant.SUCCESS);
        return response;
    }

    @Override
    public AbstractResponse getEmployeeTrackingById(Integer id, String token) throws ParseException {
        TimeTracking timeTracking = timeTrackingRepository.findById(id).get();
        TimeTrackingResponse timeTrackingResponse = new TimeTrackingResponse();
        timeTrackingResponse.setId(timeTracking.getId());
        Employee employee = employeeRepository.findByEmployeeId(timeTracking.getEmployeeId());
        timeTrackingResponse.setEmployeeName(employee.getEmployeeName());
        timeTrackingResponse.setTag(employee.getTags());
        timeTrackingResponse.setJobType(employeeJobTypeRepository.findById(timeTracking.getJobId()).get().getJobName());
        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timeTrackingResponse.setClockInTime(formatTime.format(sdf1.parse(timeTracking.getClockIn())));
        timeTrackingResponse.setClockInLocation(locationRepository.findById(timeTracking.getClockInLocation()).get().getJobSide());
        timeTrackingResponse.setClockInLocationId(timeTracking.getClockInLocation());
        if(timeTracking.getClockOut()==null || timeTracking.getEmployeeState().equals(EmployeeState.temporary_clockout.toString())){
            timeTrackingResponse.setClockOutTime("");
            timeTrackingResponse.setClockOutLocation("");
        }
        else {
            timeTrackingResponse.setClockOutLocationId(timeTracking.getClockOutLocation());
            timeTrackingResponse.setClockOutLocation(locationRepository.findById(timeTracking.getClockOutLocation()).get().getJobSide());
            timeTrackingResponse.setClockOutTime(formatTime.format(sdf1.parse(timeTracking.getClockOut())));
        }
        System.out.println("..." +timeTracking.getBreakIn() +" 000"+ timeTracking.getBreakOut());
        if(timeTracking.getBreakInLocation()==null){
            timeTrackingResponse.setBreakInTime("");
            timeTrackingResponse.setBreakInLocation("");
        }else{

            timeTrackingResponse.setBreakInLocationId(timeTracking.getBreakInLocation());
            timeTrackingResponse.setBreakInTime(formatTime.format(sdf1.parse(timeTracking.getBreakIn())));
            timeTrackingResponse.setBreakInLocation(locationRepository.findById(timeTracking.getBreakInLocation()).get().getJobSide());
        }
        if(timeTracking.getBreakOutLocation()==null) {
            timeTrackingResponse.setBreakOutTime("");
            timeTrackingResponse.setBreakOutLocation("");
        }else{
            timeTrackingResponse.setBreakOutLocationId(timeTracking.getBreakOutLocation());
            timeTrackingResponse.setBreakOutTime(formatTime.format(sdf1.parse(timeTracking.getBreakOut())));
            timeTrackingResponse.setBreakOutLocation(locationRepository.findById(timeTracking.getBreakOutLocation()).get().getJobSide());
        }
        List<TravelDetail> travelDetailList = new ArrayList<TravelDetail>();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        List<TravelDetails> travelDetails = travelDetailRepository.findByEmployeeIdAndCreatedDateContaining(timeTracking.getEmployeeId(),sdf2.format(sdf1.parse(timeTracking.getCreatedDate())));
        for(TravelDetails travelDetails1:travelDetails){
            TravelDetail travelDetail = new TravelDetail();
            travelDetail.setId(travelDetails1.getId());
            travelDetail.setTravelInTime(formatTime.format(sdf1.parse(travelDetails1.getTravelIn())));
            travelDetail.setTravelInLocation(locationRepository.findById(travelDetails1.getTravelInLocations()).get().getJobSide());
            travelDetail.setTravelInLocationId(travelDetails1.getTravelInLocations());
            if(travelDetails1.getTravelOut() == null){
                travelDetail.setTravelOutTime("");
                travelDetail.setTravelOutLocation("");
            }
            else{
                //travelHours = travelHours + hoursDifference(sdf1.parse(travelDetails1.getTravelIn()),sdf1.parse(travelDetails1.getTravelOut()));
                travelDetail.setTravelOutTime(formatTime.format(sdf1.parse(travelDetails1.getTravelOut())));
                travelDetail.setTravelOutLocation(locationRepository.findById(travelDetails1.getTravelOutLocations()).get().getJobSide());
                travelDetail.setTravelOutLocationId(travelDetails1.getTravelOutLocations());
            }
            travelDetailList.add(travelDetail);
        }
        timeTrackingResponse.setTravelDetailList(travelDetailList);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        timeTrackingResponse.setCurrentDate(sdf.format(sdf1.parse(timeTracking.getCreatedDate())));
        return timeTrackingResponse;
    }

    @Override
    public AbstractResponse updateJobTypeById(int id, String token, EmployeeJobTypeRequest request) {
        AbstractResponse response = new AbstractResponse();
        tokenService.getUserIdByToken(token);
        EmployeeJobType jobType = employeeJobTypeRepository.findById(id).get();
        jobType.setJobName(request.getJobName());
        jobType.setImageName(request.getImageName());
        employeeJobTypeRepository.save(jobType);
        response.setMessage(AppConstant.SUCCESS);
        response.setFrontendMessage(AppConstant.EMPLOYEE_JOB_TYPE_UPDATE);
        return response;
    }

    @Override
    public AbstractResponse updateEmployeeTrackingById(Integer id, String token, UpdateTimetrackingRequest request) throws ParseException {
        String userId = tokenService.getUserIdByToken(token);
        TimeTracking timeTracking = timeTrackingRepository.findById(id).get();
        timeTracking.setClockOutLocation(request.getClockOutLocation());
        timeTracking.setClockInLocation(request.getClockInLocation());
        timeTracking.setBreakInLocation(request.getBreakInLocation());
        timeTracking.setBreakOutLocation(request.getBreakOutLocation());
        String[] date = new String[2];
        date = timeTracking.getCreatedDate().split(" ");
        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
        SimpleDateFormat sdf1 = new SimpleDateFormat(  date[0] +" hh:mm:ss");
        if(request.getClockInTime().length()>2) {
            timeTracking.setClockIn(sdf1.format( formatTime.parse( request.getClockInTime())));
        }
        if(request.getClockOutTime().length()>2) {
            timeTracking.setClockOut(sdf1.format(formatTime.parse(request.getClockOutTime())));
        }if(request.getBreakInTime().length()>2) {
            timeTracking.setBreakIn(sdf1.format(formatTime.parse(request.getBreakInTime())));
        }if(request.getBreakOutTime().length()>2){
        timeTracking.setBreakOut(sdf1.format(formatTime.parse(request.getBreakOutTime())));}
        for(TravelDetail travelDetail: request.getTravelDetailList()){
            TravelDetails travelDetails = travelDetailRepository.findById(travelDetail.getId()).get();
            travelDetails.setTravelInLocations(travelDetail.getTravelInLocationId());
            travelDetails.setTravelOutLocations(travelDetail.getTravelOutLocationId());
            if(travelDetail.getTravelInTime().length()>2){
            travelDetails.setTravelIn(sdf1.format(formatTime.parse(travelDetail.getTravelInTime())));}
            if(travelDetail.getTravelOutTime().length()>2){
            travelDetails.setTravelOut(sdf1.format(formatTime.parse(travelDetail.getTravelOutTime())));}
            travelDetailRepository.save(travelDetails);
        }
        timeTracking.setNotes(request.getNotes());
        System.out.println("Request : "+ new Gson().toJson(timeTracking));
        timeTrackingRepository.save(timeTracking);
        AbstractResponse response = new AbstractResponse();
        response.setMessage(AppConstant.SUCCESS);
        return response;
    }

    @Override
    public AdminDetial getProfile(String token) {
        String userId = tokenService.getUserIdByToken(token);
        AdminDetails adminDetails = adminRepository.findByUserId(userId);
        AdminDetial adminDetial = new AdminDetial();
        adminDetial.setEmail(adminDetails.getEmail());
        adminDetial.setMobile(adminDetails.getMobile());
        adminDetial.setName(adminDetails.getName());
        return adminDetial;
    }

    @Override
    public AbstractResponse updateProfile(String token, AdminDetailsRequest request) {
        String userId = tokenService.getUserIdByToken(token);
        AdminDetails adminDetails = adminRepository.findByUserId(userId);
        adminDetails.setEmail(request.getEmail());
        adminDetails.setName(request.getName());
        adminDetails.setMobile(request.getMobile());
        adminRepository.save(adminDetails);
        AbstractResponse response = new AbstractResponse();
        response.setMessage(AppConstant.SUCCESS);

        return response;
    }

    @Override
    public AbstractResponse updatePassword(String token, String id, UpdatePasswordRequest request) {
        AbstractResponse response = new AbstractResponse();
        String userId = tokenService.getUserIdByToken(token);
        Employee emp = employeeRepository.findByEmployeeId(id);
        if (emp == null) {
            throw new ValidationException(AppConstant.FAILURE, ValidationExceptionFEMessageConstant.USER_NOT_FOUND);
        }
        emp.setPassword(passwordEncoder.encode(request.getPassword()));
        employeeRepository.save(emp);
        response.setMessage(AppConstant.SUCCESS);
        return response;
    }

    @Override
    public ResponseEntity<ByteArrayResource> exportReport(String token, ExportRequest request) throws ParseException, IOException {
        tokenService.getUserIdByToken(token);
        String filename = "Report.xlsx";
        File file1 = new File(path+filename);
        if(file1.exists()){
            file1.delete();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String[] dates = new String[2];
        if(request.getDateRange()!=null && !request.getDateRange().isBlank()) {
            dates = request.getDateRange().split("-");
        }
        Date startDate = simpleDateFormat.parse(dates[0].trim());
        Date endDate = simpleDateFormat.parse(dates[1].trim());
        Calendar c = Calendar.getInstance();
        c.setTime(endDate);
        c.add(Calendar.DATE, 1);
        endDate = c.getTime();
        String date = format.format(startDate);
        List<TimeTracking> timeTrackingList = new ArrayList<TimeTracking>();
        boolean job = false;
        boolean employeeFilter = false;
        boolean tag = false;
        boolean location = false;
        if(request.getJobId()!=null&&request.getJobId()!=0){
            job = true;
        }
        if(request.getTag()!=null && !request.getTag().equals("all")){
            tag = true;
        }
        if(request.getEmployeeId()!=null&&!request.getEmployeeId().equals("all")){
            employeeFilter = true;
        }
        if(request.getLocationId()!=null&&request.getLocationId()!=0){
            location = true;
        }
        if(job){
            if(employeeFilter){
                if(location){
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList = timeTrackingRepository.findByEmployeeIdAndJobIdAndClockInLocationAndCreatedDateContaining(request.getEmployeeId(), request.getJobId(), request.getLocationId(), date);
                    }else{
                        timeTrackingList = timeTrackingRepository.findByEmployeeIdAndJobIdAndClockInLocation(request.getEmployeeId(), request.getJobId(), request.getLocationId(), startDate, endDate);
                    }
                }else{
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList = timeTrackingRepository.findByEmployeeIdAndJobIdAndCreatedDateContaining(request.getEmployeeId(), request.getJobId(), date);
                    }else{
                        timeTrackingList = timeTrackingRepository.findByEmployeeIdAndJobId(request.getEmployeeId(), request.getJobId(), startDate, endDate);
                    }
                }
            }else{
                if(location){
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList = timeTrackingRepository.findByJobIdAndClockInLocationAndCreatedDateContaining(request.getJobId(), request.getLocationId(), date);
                    }else{
                        timeTrackingList = timeTrackingRepository.findByJobIdAndClockInLocation(request.getJobId(), request.getLocationId(), startDate, endDate);
                    }
                }else{
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList = timeTrackingRepository.findByJobIdAndCreatedDateContaining(request.getJobId(), date);
                    }else{
                        timeTrackingList = timeTrackingRepository.findByJobId(request.getJobId(), startDate,endDate);
                    }
                    }
            }
        }else{
            if(employeeFilter){
                if(location){
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList = timeTrackingRepository.findByEmployeeIdAndClockInLocationAndCreatedDateContaining(request.getEmployeeId(), request.getLocationId(), date);
                    }else{
                        timeTrackingList = timeTrackingRepository.findByEmployeeIdAndClockInLocation(request.getEmployeeId(), request.getLocationId(), startDate,endDate);
                    }
                    }else{
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList.add(timeTrackingRepository.findByEmployeeIdAndCreatedDateContaining(request.getEmployeeId(), date));
                    }else{
                        timeTrackingList = timeTrackingRepository.findByEmployeeId(request.getEmployeeId(), startDate, endDate);
                    }
                    }
            }else{
                if(location){
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList = timeTrackingRepository.findByClockInLocationAndCreatedDateContaining(request.getLocationId(), date);
                    }else{
                        timeTrackingList = timeTrackingRepository.findByClockInLocation(request.getLocationId(), startDate, endDate);
                    }
                    }else{
                    if(dates[0].trim().equals(dates[1].trim())) {
                        timeTrackingList = timeTrackingRepository.findByCreatedDateContaining(date);
                    }else{
                        timeTrackingList = timeTrackingRepository.findByDate(startDate, endDate);
                    }
                    }
            }
        }
        List<TimeTrackingResponse> list = new ArrayList<>();
        double totalWorkingHours = 0;
        double totalTravellingHours = 0;
        double totalBreakHours = 0;

        for (TimeTracking timeTracking: timeTrackingList){
            TimeTrackingResponse timeTrackingResponse = new TimeTrackingResponse();
            timeTrackingResponse.setId(timeTracking.getId());
            System.out.println("Employee id: "+ timeTracking.getEmployeeId());
            Employee employee = employeeRepository.findByEmployeeId(timeTracking.getEmployeeId());
            timeTrackingResponse.setEmployeeName(employee.getEmployeeName());
            timeTrackingResponse.setEmployeeId(employee.getEmployeeId());
            timeTrackingResponse.setTag(employee.getTags());
            timeTrackingResponse.setJobType(employeeJobTypeRepository.findById(timeTracking.getJobId()).get().getJobName());
            SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            timeTrackingResponse.setClockInTime(formatTime.format(sdf1.parse(timeTracking.getClockIn())));
            LocationInfo locationInfo = locationRepository.findById(timeTracking.getClockInLocation()).get();
            String hours = "";
            timeTrackingResponse.setProjectNumber(locationInfo.getProjectNumber());
            timeTrackingResponse.setProjectName(locationInfo.getProjectName());
            double workingHours = 0;
            double calculateTravelHours = 0;
            double calculateBreakHours = 0;
            double breakHours = 0;
            double travelHours = 0;
            if(timeTracking.getBreakInLocation()==null){
                timeTrackingResponse.setBreakInTime("");
            }else{
                timeTrackingResponse.setBreakInTime(formatTime.format(sdf1.parse(timeTracking.getBreakIn())));}
            if(timeTracking.getBreakOutLocation()==null) {
                timeTrackingResponse.setBreakOutTime("");
                timeTrackingResponse.setBreakHours("0");
            }else{
                timeTrackingResponse.setBreakOutTime(formatTime.format(sdf1.parse(timeTracking.getBreakOut())));
                breakHours = hoursDifference(sdf1.parse(timeTracking.getBreakIn()),sdf1.parse(timeTracking.getBreakOut()));
                calculateBreakHours = breakHours;
                timeTrackingResponse.setBreakOutLocation(locationRepository.findById(timeTracking.getBreakOutLocation()).get().getJobSide());
                if(breakHours<0){
                    breakHours = - breakHours;
                }

                if(breakHours<=30){
                    totalBreakHours = totalBreakHours + 30;
                    timeTrackingResponse.setBreakHours("0.5");
                }
                else{
                    totalBreakHours = totalBreakHours + breakHours;
                        String breakh = "";
                        breakh = breakh + round(breakHours/60,2);
                        timeTrackingResponse.setBreakHours(breakh);

                }
            }
            List<TravelDetail> travelDetailList = new ArrayList<TravelDetail>();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            List<TravelDetails> travelDetails = travelDetailRepository.findByEmployeeIdAndCreatedDateContaining(timeTracking.getEmployeeId(),sdf2.format(sdf1.parse(timeTracking.getCreatedDate())));
            for(TravelDetails travelDetails1:travelDetails){
                TravelDetail travelDetail = new TravelDetail();
                travelDetail.setTravelInTime(formatTime.format(sdf1.parse(travelDetails1.getTravelIn())));
                travelDetail.setTravelInLocation(locationRepository.findById(travelDetails1.getTravelInLocations()).get().getJobSide());
                if(travelDetails1.getTravelOut() == null){
                    travelDetail.setTravelOutTime("");
                    travelDetail.setTravelOutLocation("");
                }
                else{
                    travelHours = travelHours + hoursDifference(sdf1.parse(travelDetails1.getTravelIn()),sdf1.parse(travelDetails1.getTravelOut()));
                    travelDetail.setTravelOutTime(formatTime.format(sdf1.parse(travelDetails1.getTravelOut())));
                    travelDetail.setTravelOutLocation(locationRepository.findById(travelDetails1.getTravelOutLocations()).get().getJobSide());
                }
                travelDetailList.add(travelDetail);
            }
            timeTrackingResponse.setTravelDetailList(travelDetailList);
            calculateTravelHours = travelHours;
            if(timeTracking.getClockOut()==null || timeTracking.getEmployeeState().equals(EmployeeState.temporary_clockout.toString())){
                timeTrackingResponse.setClockOutTime("");
                timeTrackingResponse.setWorkingHours("0");
            }
            else {
                System.out.println("Error occured here : "+timeTracking.getId());
                timeTrackingResponse.setClockOutTime(formatTime.format(sdf1.parse(timeTracking.getClockOut())));
                timeTrackingResponse.setClockOutLocation(locationRepository.findById(timeTracking.getClockOutLocation()).get().getJobSide());
                if(calculateBreakHours<0){
                    calculateBreakHours = - calculateBreakHours;
                }
                if(calculateTravelHours<0){
                    calculateTravelHours = -calculateTravelHours;
                }
                workingHours = hoursDifference(sdf1.parse(timeTracking.getClockIn()),sdf1.parse(timeTracking.getClockOut()));
                if(workingHours<0){
                    workingHours = - workingHours;
                }
                workingHours = workingHours -calculateBreakHours-calculateTravelHours;
                totalWorkingHours = totalWorkingHours + workingHours;
                    String breakh = "";
                    breakh = breakh + round(workingHours/60,2) ;
                    timeTrackingResponse.setWorkingHours(breakh);

            }
            if(travelHours<0){
                travelHours = - travelHours;
            }
            totalTravellingHours = totalTravellingHours + travelHours;
                String breakh = "";
                breakh = breakh + round(travelHours/60,2);
                timeTrackingResponse.setTravelHours(breakh);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            timeTrackingResponse.setCurrentDate(sdf.format(sdf1.parse(timeTracking.getCreatedDate())));
            if(tag && request.getTag().equals(employee.getTags())) {
                list.add(timeTrackingResponse);
            }
            if(!tag){
                list.add(timeTrackingResponse);
            }
        }
        XSSFWorkbook workbook = new XSSFWorkbook();
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        XSSFSheet sheet = workbook.createSheet("Time Tracking Details");
        Map<Integer, Object[]> tableData = new TreeMap<Integer, Object[]>();
        int count = 2;
        String totalWorking = "";
        String totalTravelling = "";
        String totalBreaking = "";
            String breakh = "";
            breakh = breakh + round(totalTravellingHours/60,2);
            totalTravelling = breakh;
            breakh = "";
            breakh = breakh + round(totalBreakHours/60,2);
            totalBreaking = breakh;
            breakh = "";
            breakh = breakh + round(totalWorkingHours/60,2);
            totalWorking = breakh;
//        tableData.put(1, new Object[]{"S.NO.","DATE","EMPLOYEE NAME","TAG","JOBTYPE","CLOCKIN TIME","CLOCKIN LOCATION","CLOCKOUT TIME","CLOCKOUT LOCATION","BREAKIN TIME","BREAKIN LOCATION","BREAKOUT TIME","BREAKOUT LOCATION","TRAVELIN TIME LIST","TRAVELIN LOCATION LIST","TRAVELOUT TIME LIST","TRAVELOUT LOCATION LIST","WORKING HOURS","TRAVELLING HOURS","BREAK HOURS"});
        tableData.put(1, new Object[]{"S.NO.","LINE DATE","EMPLOYEE NAME","WORKER ID","PROJECT NUMBER","PROJECT NAME","TAG","JOBTYPE","CLOCKIN TIME","CLOCKIN LOCATION","CLOCKOUT TIME","CLOCKOUT LOCATION","STARTBREAK TIME","STARTBREAK LOCATION","ENDBREAK TIME","ENDBREAK LOCATION","STARTTRAVEL TIME LIST","STARTTRAVEL LOCATION LIST","ENDTRAVEL TIME LIST","ENDTRAVEL LOCATION LIST","WORKING HOURS","TRAVELLING HOURS","BREAK HOURS"});

        for(TimeTrackingResponse timeTrackingResponse:list){
            List<String> travelInTime = new ArrayList<String>();
            List<String> travelOutTime = new ArrayList<String>();
            List<String> travelInLocation = new ArrayList<String>();
            List<String> travelOutLocation = new ArrayList<String>();
            for(TravelDetail travelDetail:timeTrackingResponse.getTravelDetailList()){
                travelInTime.add(travelDetail.getTravelInTime());
                travelInLocation.add(travelDetail.getTravelInLocation());
                if(travelDetail.getTravelOutTime()!=null){
                    travelOutTime.add(travelDetail.getTravelOutTime());
                    travelOutLocation.add(travelDetail.getTravelOutLocation());
                }
            }
            tableData.put(count,new Object[]{String.valueOf(count-1),timeTrackingResponse.getCurrentDate(),timeTrackingResponse.getEmployeeName(),timeTrackingResponse.getEmployeeId(),timeTrackingResponse.getProjectNumber(),timeTrackingResponse.getProjectName(),timeTrackingResponse.getTag(),timeTrackingResponse.getJobType(),
            timeTrackingResponse.getClockInTime(),timeTrackingResponse.getClockInLocation(),timeTrackingResponse.getClockOutTime(),timeTrackingResponse.getClockOutLocation(),timeTrackingResponse.getBreakInTime(),timeTrackingResponse.getBreakInLocation(),
            timeTrackingResponse.getBreakOutTime(),timeTrackingResponse.getBreakOutLocation(),String.join(";",travelInTime),String.join(";",travelInLocation),String.join(";",travelOutTime),String.join(";",travelOutLocation),
            timeTrackingResponse.getWorkingHours(),timeTrackingResponse.getTravelHours(),timeTrackingResponse.getBreakHours()});
            count++;
        }
        if(employeeFilter) {
            tableData.put(count + 1, new Object[]{"", "", "", "", "", "",
                    "", "", "", "", "", "",
                    "", "", "", "", "", "",
                    totalWorking, totalTravelling, totalBreaking});
        }
        Set<Integer> keyset = tableData.keySet();
        int rownum = 0 ;
        for(Integer key : keyset){
            Row row = sheet.createRow(rownum++);
            Object[] objarray = tableData.get(key);
            int cellnum = 0;
            for(Object obj : objarray){
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String){
                    cell.setCellValue((String) obj);
                }
            }
        }
        File file = new File(path+filename);
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        out.close();
        byte[] content = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(content);
            for (int i = 0; i < content.length; i++) {
                System.out.print((char)content[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.");
            e.printStackTrace();
        }
        final ByteArrayResource resource = new ByteArrayResource(content);
        return ResponseEntity
                .ok()
                .contentLength(content.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + filename + "\"")
                .body(resource);
    }

    public long hoursDifference(Date date1, Date date2) {
        return TimeUnit.MILLISECONDS.toMinutes(date1.getTime()-date2.getTime());
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}



