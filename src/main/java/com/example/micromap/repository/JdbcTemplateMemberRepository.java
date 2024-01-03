package com.example.micromap.repository;

import com.example.micromap.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", member.getUserId());
        parameters.put("password", member.getPassword());

//        String key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setUser_id(key.StringValue());
        jdbcInsert.execute(parameters);  // Execute the insert operation

        return member;
    }

    @Override
    public Optional<Member> findById(String user_id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), user_id);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    @Override
    public String delete(String id) {
        //jdbc templates - 삽입/수정/삭제 : update()사용
        String delete_sql = "delete member where id = ?";
        jdbcTemplate.update(delete_sql, id);
        return id;
    }

    @Override
    public Optional<Member> updateId(String previous_id, String new_id) {
        //기존 id, 새로운 id
        String update_id = "update member set id=? where id = ?";
        jdbcTemplate.update(update_id, new_id, previous_id);

        //아이디 바뀌었는지 확인
        String check_query = "select * from member where id = ?";
        List<Member> result = jdbcTemplate.query(check_query,  memberRowMapper(), new_id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> updatePassword(Member member, String new_password) {

        String update_password = "update member set password=? where id = ?";
        jdbcTemplate.update(update_password, new_password, member.getUserId());
        List<Member> result= jdbcTemplate.query("select * from member where id=? AND password=?",  memberRowMapper(), member.getUserId(), new_password);
        return result.stream().findAny();
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setUserId(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}
