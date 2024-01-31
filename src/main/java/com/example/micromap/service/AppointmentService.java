package com.example.micromap.service;

import com.example.micromap.domain.Appointment;
import com.example.micromap.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public Long createAppointment(Appointment appointment){
        return appointmentRepository.createAppointment(appointment);
    }

    public Optional<Appointment> selectOneAppointment(Long appointment_id, String user_id){
        return appointmentRepository.selectOneAppointment(appointment_id, user_id);
    }

    public List<Appointment> selectAllAppointmentList(String user_id){
        return appointmentRepository.selectAllAppointmentList(user_id);
    }

    public List<Appointment> selectBeforeAppointmentList(String user_id){
        return appointmentRepository.selectBeforeAppointmentList(user_id);
    }

    public List<Appointment> selectAfterAppointmentList(String user_id){
        return appointmentRepository.selectAfterAppointmentList(user_id);
    }

    public String finishAppointment(Long appointment_id){
        return appointmentRepository.finishAppointment(appointment_id);
    }

    public Long deleteAppointment(Long appointment_id){
        return appointmentRepository.deleteAppointment(appointment_id);
    }
}
