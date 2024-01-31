package com.example.micromap.controller;

import com.example.micromap.domain.Appointment;
import com.example.micromap.service.AppointmentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Tag(name = "Appointment API", description = "Swagger Test API For Appointment")
@RequestMapping("/")
@RestController
public class AppointmentController {
    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/create_appointment/{label}/{restaurant_id}/{user_id}/{date}/{friends_list}/{is_finished}", method = RequestMethod.POST)
    public Long createAppointment(
            @Parameter(description = "약속 내용")
            @PathVariable(name = "label") String label,
            @Parameter(description = "가게 아이디")
            @PathVariable(name = "restaurant_id") Long restaurant_id,
            @Parameter(description = "유저 아이디")
            @PathVariable(name = "user_id") String user_id,
            @Parameter(description = "약속 날짜", example = "2022-01-17T15:45:30")
            @PathVariable(name = "date")LocalDateTime date,
            @Parameter(description = "약속에 포함된 친구 아이디 리스트", example = "[\"doo\", \"doko\"]")
            @PathVariable(name = "friends_list")List<String> friends_list,
            @Parameter(description = "약속 수행?")
            @PathVariable(name = "is_finished") Boolean is_finished
            ){
        Appointment appointment = new Appointment();
        appointment.setLabel(label);
        appointment.setRestaurantId(restaurant_id);
        appointment.setUserId(user_id);
        appointment.setDate(date);
        appointment.setFriendsList(friends_list);
        appointment.setFinished(is_finished);

        return appointmentService.createAppointment(appointment);
    }

    @RequestMapping(value = "select_one_appointment/{appointment_id}/{user_id}", method = RequestMethod.GET)
    public Optional<Appointment> selectOneAppointment(
            @Parameter(description = "약속 아이디")
            @PathVariable(name = "appointment_id") Long appointment_id,
            @Parameter(description = "유저 아이디")
            @PathVariable(name = "user_id") String user_id
    ){
        return appointmentService.selectOneAppointment(appointment_id, user_id);
    }

    @RequestMapping(value = "select_all_appointment/{user_id}", method = RequestMethod.GET)
    public List<Appointment> selectAllAppointment(
            @Parameter(description = "유저 아이디")
            @PathVariable(name = "user_id") String user_id){
        return appointmentService.selectAllAppointmentList(user_id);
    }

    @RequestMapping(value = "select_before_appointment/{user_id}", method = RequestMethod.GET)
    public List<Appointment> selectBeforeAppointmentList(
            @Parameter(description = "유저 아이디")
            @PathVariable(name = "user_id") String user_id){
        return appointmentService.selectBeforeAppointmentList(user_id);
    }

    @RequestMapping(value = "select_after_appointment/{user_id}", method = RequestMethod.GET)
    public List<Appointment> selectAfterAppointmentList(
            @Parameter(description = "유저 아이디")
            @PathVariable(name = "user_id") String user_id){
        return appointmentService.selectAfterAppointmentList(user_id);
    }

    @RequestMapping(value = "finish_appointment/{appointment_id}", method = RequestMethod.PUT)
    public String finishAppointment(
            @Parameter(description = "약속 아이디")
            @PathVariable(name = "appointment_id") Long appointment_id
    ){
        return appointmentService.finishAppointment(appointment_id);
    }

    @RequestMapping(value = "delete_appointment/{appointment_id}", method = RequestMethod.DELETE)
    public Long deleteAppointment(
            @Parameter(description = "약속 아이디")
            @PathVariable(name = "appointment_id") Long appointment_id){
        return appointmentService.deleteAppointment(appointment_id);
    }
}
