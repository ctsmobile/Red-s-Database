package com.cts.redplastring.redplastringapplication.scheduler;

import com.cts.redplastring.redplastringapplication.enums.EmployeeState;
import com.cts.redplastring.redplastringapplication.model.*;
import com.cts.redplastring.redplastringapplication.repository.*;
import com.cts.redplastring.redplastringapplication.request.EmployeeRequest;
import com.cts.redplastring.redplastringapplication.services.EmployeeServiceImpl;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedplasteringScheduler {

    @Autowired
    private TimeTrackingRepository timeTrackingRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TravelDetailRepository travelDetailRepository;

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private NotificationDetailRepository notificationDetailRepository;
    @Autowired
    private ApiLogRepository apiLogRepository;

    /*@Scheduled(fixedRate = 60000)
    public void reminder() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<TimeTracking> timeTrackingList = timeTrackingRepository.findByCreatedDateContaining(sdf.format(new Date()));
        for (TimeTracking timeTracking:timeTrackingList){
            Employee employee = employeeRepository.findByEmployeeId(timeTracking.getEmployeeId());
            LocationInfo locationInfo =  locationRepository.findById(timeTracking.getClockInLocation()).get();
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date shiftEndTime = parser.parse(sdf.format(new Date())+" "+locationInfo.getShiftEndTime());
            if(new Date().after(shiftEndTime)||new Date().equals(shiftEndTime)){
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                timeTracking.setEmployeeState(EmployeeState.checkedOut.toString());
                String currentDate = sdf1.format(new Date());
                timeTracking.setClockOut(currentDate);
                timeTracking.setClockOutLocation(1);
                if(timeTracking.getBreakIn()!=null && timeTracking.getBreakOut()==null){
                    timeTracking.setBreakOut(currentDate);
                    timeTracking.setBreakOutLocation(1);
                }
                TravelDetails travelDetails = travelDetailRepository.findByEmployeeIdAndCreatedDateContainingAndTravelOutIsNull(timeTracking.getEmployeeId(), sdf.format(new Date()));
                if(travelDetails !=null){
                    travelDetails.setTravelOut(currentDate);
                    travelDetails.setTravelOutLocations(1);
                    travelDetailRepository.save(travelDetails);
                }
                timeTrackingRepository.save(timeTracking);
                employee.setEmployeeState(EmployeeState.checkedOut.toString());
                employeeRepository.save(employee);
            }
        }
    }*/
//    @Scheduled(fixedRate = 1000)
    public void removeLog () throws ParseException {
        List<ApiLog> apiLogs = apiLogRepository.findAll();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (ApiLog api: apiLogs){
            String date = api.getCreatedDate();
            String today = sdf.format(new Date());


            long diffInMillies = Math.abs(sdf.parse(today).getTime() - sdf.parse(date).getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff > 3){
                apiLogRepository.delete(api);
            }
        }
    }

    /*@Scheduled(fixedRate = 60000)
    public void timeOut() throws ParseException, JSONException {
        System.out.println("Scheduler is working for time out conditions");
        List<Employee> employees =employeeRepository.findAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        for (Employee employee :employees){
//            TimeTracking timeTrackingList = timeTrackingRepository.findByEmployeeIdAndCreatedDateContaining(employee.getEmployeeId(), sdf.format(new Date()));
            TimeTracking timeTrackingList = timeTrackingRepository.findByEmployeeIdAndCreatedDate(employee.getEmployeeId(), sdf1.format(new Date()));
            TravelDetails travelDetails = travelDetailRepository.findByEmployeeIdAndCreatedDate(employee.getEmployeeId(), sdf1.format(new Date()));
            try {
                if (employee.getEmployeeState().equals(EmployeeState.breakIn.toString())){
                    long  breakHours = hoursDifference(sdf.parse(timeTrackingList.getBreakIn()),new Date());
                    if (breakHours >= 40 && breakHours < 49 && !timeTrackingList.isReminder40()){
                        String messageBody = "Break time exceeded over 40 minutes";
                        String title = "Reminder";
                        EmployeeRequest employeeRequest = new EmployeeRequest();
                        employeeRequest.setFcmKey(employee.getFcmKey());
                        NotificationDetail notificationDetail = new NotificationDetail();
                        notificationDetail.setNotification(messageBody);
                        notificationDetail.setUserId(employee.getUserId());
                        notificationDetail.setResponse(new Gson().toJson(employeeRequest));
                        notificationDetail.setCreatedDate(sdf.format(new Date()));
                        notificationDetailRepository.save(notificationDetail);
                        timeTrackingList.setReminder40(true);
                        timeTrackingRepository.save(timeTrackingList);
                        JSONObject notification = new JSONObject();
                        notification.put("title", title);
                        notification.put("messageBody", messageBody);
                        employeeRequest.setNotificationId(notificationDetailRepository.save(notificationDetail).getId());
                        notification.put("response", new Gson().toJson(employeeRequest));
                        employeeService.sendNotification(employee.getFcmKey(),notification);
                    }else if (breakHours >= 50 && breakHours < 59 && !timeTrackingList.isReminder50()){
                        String messageBody = "Break time exceeded over 50 minutes";
                        String title = "Reminder";
                        EmployeeRequest employeeRequest = new EmployeeRequest();
                        employeeRequest.setFcmKey(employee.getFcmKey());
                        NotificationDetail notificationDetail = new NotificationDetail();
                        notificationDetail.setNotification(messageBody);
                        notificationDetail.setUserId(employee.getUserId());
                        notificationDetail.setResponse(new Gson().toJson(employeeRequest));
                        notificationDetail.setCreatedDate(sdf.format(new Date()));
                        notificationDetailRepository.save(notificationDetail);
                        timeTrackingList.setReminder50(true);
                        timeTrackingRepository.save(timeTrackingList);
                        JSONObject notification = new JSONObject();
                        notification.put("title", title);
                        notification.put("messageBody", messageBody);
                        employeeRequest.setNotificationId(notificationDetailRepository.save(notificationDetail).getId());
                        notification.put("response", new Gson().toJson(employeeRequest));
                        employeeService.sendNotification(employee.getFcmKey(),notification);
                    }
                    else if (breakHours >= 60) {
                        timeTrackingList.setEmployeeState(EmployeeState.breakOut.toString());
                        timeTrackingList.setBreakOut(sdf.format(new Date()));
                        timeTrackingList.setBreakOutLocation(0);
                        timeTrackingRepository.save(timeTrackingList);
                        employee.setEmployeeState(EmployeeState.breakOut.toString());
                        employeeRepository.save(employee);

                    }

                }
                else if (employee.getEmployeeState().equals(EmployeeState.travelIn.toString())) {
                    long travelHours = hoursDifference(sdf.parse(timeTrackingList.getTravelIn()),new Date());
                    if (travelHours >= 40 && travelHours < 49 && !timeTrackingList.isReminder40()){
                        String messageBody = "Travel time exceeded over 40 minutes";
                        String title = "Reminder";
                        EmployeeRequest employeeRequest = new EmployeeRequest();
                        employeeRequest.setFcmKey(employee.getFcmKey());
                        NotificationDetail notificationDetail = new NotificationDetail();
                        notificationDetail.setNotification(messageBody);
                        notificationDetail.setUserId(employee.getUserId());
                        notificationDetail.setResponse(new Gson().toJson(employeeRequest));
                        notificationDetail.setCreatedDate(sdf.format(new Date()));
                        notificationDetailRepository.save(notificationDetail);
                        timeTrackingList.setReminder40(true);
                        timeTrackingRepository.save(timeTrackingList);

                        JSONObject notification = new JSONObject();
                        notification.put("title", title);
                        notification.put("messageBody", messageBody);
                        employeeRequest.setNotificationId(notificationDetailRepository.save(notificationDetail).getId());
                        notification.put("response", new Gson().toJson(employeeRequest));
                        employeeService.sendNotification(employee.getFcmKey(),notification);
                    } else if (travelHours >= 50 && travelHours < 59 && !timeTrackingList.isReminder50()) {
                        String messageBody = "Travel time exceeded over 50 minutes";
                        String title = "Reminder";
                        EmployeeRequest employeeRequest = new EmployeeRequest();
                        employeeRequest.setFcmKey(employee.getFcmKey());
                        NotificationDetail notificationDetail = new NotificationDetail();
                        notificationDetail.setNotification(messageBody);
                        notificationDetail.setUserId(employee.getUserId());
                        notificationDetail.setResponse(new Gson().toJson(employeeRequest));
                        notificationDetail.setCreatedDate(sdf.format(new Date()));
                        notificationDetailRepository.save(notificationDetail);
                        timeTrackingList.setReminder50(true);
                        timeTrackingRepository.save(timeTrackingList);

                        JSONObject notification = new JSONObject();
                        notification.put("title", title);
                        notification.put("messageBody", messageBody);
                        employeeRequest.setNotificationId(notificationDetailRepository.save(notificationDetail).getId());
                        notification.put("response", new Gson().toJson(employeeRequest));
                        employeeService.sendNotification(employee.getFcmKey(),notification);
                    } else if (travelHours >= 60) {
                        travelDetails.setTravelOut(sdf.format(new Date()));
                        travelDetails.setTravelOutLocations(0);
                        travelDetailRepository.save(travelDetails);
                        timeTrackingList.setEmployeeState(EmployeeState.travelOut.toString());
                        timeTrackingRepository.save(timeTrackingList);
                        employee.setEmployeeState(EmployeeState.travelOut.toString());
                        employeeRepository.save(employee);
                    }
                }
            } catch (NullPointerException e) {
                System.out.println(e);
                System.out.println(employee.getEmployeeName() + " have state as null ");
                System.out.println();
            }
        }

    }*/




    @Scheduled(fixedRate = 60000)
    public void clockout() throws ParseException {
        System.out.println("Scheduler is working");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<TimeTracking> timeTrackingList = timeTrackingRepository.findByClockOutIsNull();
        System.out.print(timeTrackingList.size());
        //List<TimeTracking> timeTrackingList = timeTrackingRepository.findByCreatedDateContainingAndClockOutIsNull(sdf.format(new Date()));
        for (TimeTracking timeTracking : timeTrackingList) {
            //List<ApiLog> logs = apiLogRepository.findByCreatedDateContainingAndEmployeeId(sdf.format(new Date()), timeTracking.getEmployeeId(),Sort.by(Sort.Direction.DESC, "createdDate"));
            Employee employee = employeeRepository.findByEmployeeId(timeTracking.getEmployeeId());
            System.out.println(employee.getEmployeeState());
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if (timeTracking.getEmployeeState().equals(EmployeeState.breakIn.toString())) {
                    Date date = sdf1.parse(timeTracking.getBreakIn());
                    System.out.println("Break time difference : " + hoursDifference(date, new Date()));
                    if (hoursDifference(date, new Date()) >= 65) {
                        timeTracking.setEmployeeState(EmployeeState.checkedOut.toString());
                        timeTracking.setClockOut(sdf1.format(new Date()));
                        timeTracking.setClockOutLocation(1);
                        timeTracking.setBreakOut(sdf1.format(new Date()));
                        timeTracking.setBreakOutLocation(1);
                        timeTrackingRepository.save(timeTracking);
                        employee.setEmployeeState(EmployeeState.checkedOut.toString());
                        employeeRepository.save(employee);
                    }
                }
                if (timeTracking.getEmployeeState().equals(EmployeeState.travelIn.toString())) {
                    TravelDetails travelDetails = travelDetailRepository.findByEmployeeIdAndCreatedDateContainingAndTravelOutIsNull(employee.getEmployeeId(), sdf.format(new Date()));
                    Date date = sdf1.parse(travelDetails.getTravelIn());
                    System.out.println("Travel time difference : " + hoursDifference(date, new Date()));
                    if (hoursDifference(date, new Date()) >= 65) {
                        timeTracking.setEmployeeState(EmployeeState.checkedOut.toString());
                        timeTracking.setClockOut(sdf1.format(new Date()));
                        timeTracking.setClockOutLocation(1);
                        travelDetails.setTravelOut(sdf1.format(new Date()));
                        travelDetails.setTravelOutLocations(1);
                        travelDetailRepository.save(travelDetails);
                        timeTrackingRepository.save(timeTracking);
                        employee.setEmployeeState(EmployeeState.checkedOut.toString());
                        employeeRepository.save(employee);
                    }
                }
                if(timeTracking.getEmployeeState().equals(EmployeeState.temporary_clockout.toString())){
                    Date date = sdf1.parse(timeTracking.getClockOut());
                    if (hoursDifference(date, new Date()) >= 15) {
                        timeTracking.setEmployeeState(EmployeeState.checkedOut.toString());
                        timeTrackingRepository.save(timeTracking);
                        employee.setEmployeeState(EmployeeState.checkedOut.toString());
                        employeeRepository.save(employee);
                    }
                }

                if (!timeTracking.getEmployeeState().equals(EmployeeState.checkedOut.toString()) && !timeTracking.getEmployeeState().equals(EmployeeState.breakIn.toString()) && !timeTracking.getEmployeeState().equals(EmployeeState.travelIn.toString())) {
                    Date date = new Date();
                    Date eod = sdf1.parse(timeTracking.getCreatedDate().substring(0,11)+"23:59:59");
                    System.out.println("eod : " + sdf1.format(eod));
                    if (date.equals(eod) || date.after(eod)) {
                        timeTracking.setEmployeeState(EmployeeState.checkedOut.toString());
                        String currentDate = sdf1.format(eod);
                        timeTracking.setClockOut(currentDate);
                        if(timeTracking.getBreakIn()!=null && timeTracking.getBreakOut()==null){
                            timeTracking.setBreakOut(currentDate);
                            timeTracking.setBreakOutLocation(1);
                        }
                        TravelDetails travelDetails = travelDetailRepository.findByEmployeeIdAndCreatedDateContainingAndTravelOutIsNull(timeTracking.getEmployeeId(), sdf.format(new Date()));
                        if(travelDetails !=null){
                            travelDetails.setTravelOut(currentDate);
                            travelDetails.setTravelOutLocations(1);
                            travelDetailRepository.save(travelDetails);
                        }
                        timeTrackingRepository.save(timeTracking);
                        employee.setPreviousTrackId(timeTracking.getId());
                        employee.setPreviousState(EmployeeState.checkedOut.toString());
                        employee.setEmployeeState(EmployeeState.checkedOut.toString());
                        employeeRepository.save(employee);
                        timeTracking.setEmployeeState(EmployeeState.checkedOut.toString());
                        timeTracking.setClockOutLocation(timeTracking.getClockInLocation());
                        timeTrackingRepository.save(timeTracking);
                        employee.setEmployeeState(EmployeeState.checkedOut.toString());
                        employeeRepository.save(employee);
                    }
                }
            }

    }

    public long hoursDifference(Date date1, Date date2) {
        long diff = TimeUnit.MILLISECONDS.toMinutes(date1.getTime()-date2.getTime());
        if(diff < 0){
            diff = - diff;
        }
        return diff;
    }
}
