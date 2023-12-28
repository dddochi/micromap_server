package com.example.micromap.repository;

import com.example.micromap.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        parameters.put("id", member.getUser_id());
        parameters.put("password", member.getPassword());

//        String key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        member.setUser_id(key.StringValue());

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
    public Member delete(Member member) {
        return null;
    }

    @Override
    public Member updateId(Member member) {
        return null;
    }

    @Override
    public Member updatePassword(Member member) {
        return null;
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setUser_id(rs.getString("id"));
            member.setPassword(rs.getString("password"));
            return member;
        };
    }
}
