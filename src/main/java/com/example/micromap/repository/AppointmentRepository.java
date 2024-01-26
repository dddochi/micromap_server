package com.example.micromap.repository;

import com.example.micromap.domain.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {
    //약속 create
    public Long createAppointment(Appointment appointment);

    //특정 약속 조회 -> 약속 아이디, 유저 아이디 필요
    public Optional<Appointment> selectOneAppointment (Long appointment_id, String user_id);

    public List<Appointment> selectAllAppointmentList(String user_id);
    //아직 이행하지 않은 약속만 보여주기
    public List<Appointment> selectBeforeAppointmentList(String user_id);
    //이행한 약속 조회
    public List<Appointment> selectAfterAppointmentList(String user_id);

    //약속 실행 버튼 클릭 -> is_finished = true
    public String finishAppointment(Long appointment_id);
    //약속 삭제 - 삭제된 아이디 리턴
    public Long deleteAppointment(Long appointment_id);
}
