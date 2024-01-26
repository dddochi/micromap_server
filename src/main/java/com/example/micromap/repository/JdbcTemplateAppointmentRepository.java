package com.example.micromap.repository;

import com.example.micromap.domain.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.*;

public class JdbcTemplateAppointmentRepository implements AppointmentRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateAppointmentRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long createAppointment(Appointment appointment) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("appointment").usingGeneratedKeyColumns("appointment_id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("label", appointment.getLabel());
        parameters.put("restaurant_id", appointment.getRestaurantId());
        parameters.put("user_id", appointment.getUserId());
        parameters.put("date", appointment.getDate());
        parameters.put("is_finished", appointment.getFinished());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource((parameters)));
        appointment.setAppointmentId(key.longValue());

//        //parameters.put("friends_list", appointment.getFriendsList());
//        for(String friendId : appointment.getFriendsList()){
//            String sql = "INSERT INTO group_member (user_id, friend_id) values (?, ?)";
//            jdbcTemplate.update(sql, appointment.getUserId(), friendId);
//        }
        List<Object[]> batchArgs = new ArrayList<>();
        for (String friendId : appointment.getFriendsList()) {
            batchArgs.add(new Object[]{appointment.getAppointmentId(), appointment.getUserId(), friendId});
        }

        String sql = "INSERT INTO group_member (appointment_id, user_id, friend_id) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, batchArgs);
        return key.longValue();
    }

    @Override
    public Optional<Appointment> selectOneAppointment(Long appointment_id, String user_id) {
        String sql = "SELECT a.appointment_id, a.label, a.restaurant_id, a.user_id, a.date, a.is_finished, gm.friend_id FROM appointment a LEFT JOIN group_member gm ON a.user_id = gm.user_id WHERE a.appointment_id = ? AND a.user_id = ?";
        List<Appointment> result = jdbcTemplate.query(sql, appointmentRowMapper(), user_id);
        return result.stream().findAny();
    }


    //특정 약속 조회 -> 약속 아이디, 유저 아이디 필요
    //모든 약속 조회 -> 약속 전 / 후 / 삭제 필요 -> 약속 날짜가 현재 날짜보다 늦으면 - 약속 전
    @Override
    public List<Appointment> selectAllAppointmentList(String user_id) {
        //String sql = "select * FROM appointment WHERE user_id = ?";
        String sql = "SELECT a.appointment_id, a.label, a.restaurant_id, a.user_id, a.date, a.is_finished FROM appointment a LEFT JOIN group_member gm ON a.user_id = gm.user_id WHERE a.user_id = ?";
        List<Appointment> result = jdbcTemplate.query(sql, appointmentRowMapper(), user_id);
        return result;
    }
    @Override
    public List<Appointment> selectBeforeAppointmentList(String user_id) {
        String sql = "SELECT * FROM appointment WHERE user_id = ? AND is_finished = false";
        List<Appointment> result = jdbcTemplate.query(sql, appointmentRowMapper(), user_id);
        return result;
    }

    @Override
    public List<Appointment> selectAfterAppointmentList(String user_id) {
        String sql = "SELECT * FROM appointment WHERE user_id = ? AND is_finished = true";
        List<Appointment> result = jdbcTemplate.query(sql, appointmentRowMapper(), user_id);
        return result;
    }
    //약속 실행 버튼 클릭 -> is_finished = true
    public String finishAppointment(Long appointment_id){
        String sql = "UPDATE appointment SET is_finished = true WHERE appointment_id = ?";
        jdbcTemplate.update(sql);
        return "Finish appointment";
    }
    //약속 삭제 - 삭제된 아이디 리턴
    public Long deleteAppointment(Long appointment_id){
        String sql = "DELETE appointment WHERE appointment_id = ?";
        jdbcTemplate.update(sql, appointment_id);
        return appointment_id;
    }

    private RowMapper<Appointment> appointmentRowMapper(){
        return ((rs, rowNum) -> {
            Appointment appointment = new Appointment();
            appointment.setAppointmentId(rs.getLong("appointment_id"));
            appointment.setLabel(rs.getString("label"));
            appointment.setUserId(rs.getString("user_id"));
            appointment.setRestaurantId(rs.getLong("restaurant_id"));
            appointment.setFinished(rs.getBoolean("is_finished"));
            LocalDateTime date = rs.getTimestamp("date").toLocalDateTime();
            appointment.setDate(date);

            List<String> friendsList = new ArrayList<>();
            do{
                String friendId = rs.getString("friend_id");
                if(friendId!=null){
                    friendsList.add(friendId);
                }
            }while (rs.next());
            appointment.setFriendsList(friendsList);
           return appointment;
        });
    }

}
