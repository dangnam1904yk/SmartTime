// package com.vinschool.smarttime.repository;

// import java.util.List;

// import org.springframework.jdbc.core.BeanPropertyRowMapper;
// import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
// import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
// import org.springframework.stereotype.Repository;

// import com.vinschool.smarttime.model.response.CheckNoonResponsive;

// import lombok.RequiredArgsConstructor;

// @Repository
// @RequiredArgsConstructor
// public class CheckNoonRepositoryImpl implements CheckNoonRepositoryCustom {

// private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

// @Override
// public List<CheckNoonResponsive>
// findByUserIdCreateAndTimeLineDtoNameJDPC(String userid, String month) {
// StringBuilder stringBuilder = new StringBuilder();
// stringBuilder.append(
// "select C.Id as Id, C.date_work as dateWork, c.id_user_trans as idUserTrans ,
// c.is_check as isCheck, c.Ma_ca as MaCa, c.name_ca as nameCa,\n"
// + //
// "note, u.full_name as fullName, id_user as idUser, id_time_line as
// idTimeLine, create_by as createBy ,c.create_date as createDate,\n"
// + //
// "is_active as isAcitve, c.update_by as updateBy, c.update_date as updateDate,
// month, u.email\n"
// + //
// "FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID \n" + //
// "Join User u on u.id = c.id_user where c.create_by =:userId and
// l.month=:month ");
// MapSqlParameterSource mapInput = new MapSqlParameterSource();
// mapInput.addValue("month", month);
// mapInput.addValue("userid", userid);
// List<CheckNoonResponsive> list =
// namedParameterJdbcTemplate.query(stringBuilder.toString(), mapInput,
// BeanPropertyRowMapper.newInstance(CheckNoonResponsive.class));
// return list;
// }

// }
