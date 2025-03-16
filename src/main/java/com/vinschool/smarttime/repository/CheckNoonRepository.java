package com.vinschool.smarttime.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vinschool.smarttime.entity.CheckNoon;
import com.vinschool.smarttime.model.response.CheckNoonResponsive;

import jakarta.transaction.Transactional;

@Repository
public interface CheckNoonRepository extends JpaRepository<CheckNoon, String> {

        @Modifying
        @Transactional
        @Query(value = "SELECT C.Id, C.date_work, c.id_user_trans, c.is_check, c.Ma_ca,c.name_ca, note, id_user, id_time_line, create_by,create_date, is_active, update_by,update_date, month FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID   WHERE C.ID_USER =:userid and L.MONTH =:timeLine", nativeQuery = true)
        public List<CheckNoon> findByUserIdWorkAndTimeLine(@Param("userid") String userid,
                        @Param("timeLine") String timeLine);

        @Modifying
        @Transactional
        @Query(value = "SELECT C.Id, C.date_work, c.id_user_trans, c.is_check, c.Ma_ca,c.name_ca, note, id_user, id_time_line, create_by,create_date, is_active,update_by, update_date, month FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID   WHERE C.CREATE_BY =:userid and L.MONTH =:timeLine", nativeQuery = true)
        public List<CheckNoon> findByUserIdCreateAndTimeLine(@Param("userid") String userid,
                        @Param("timeLine") String timeLine);

        // @Query(value = "SELECT new
        // com.vinschool.smarttime.model.response.CheckNoonResponsive(C.Id, C.date_work,
        // c.id_user_trans, c.is_check, c.Ma_ca,c.name_ca, note, u.full_name, \n"
        // + //
        // "id_user, id_time_line, create_by,c.create_date, is_active,c.update_by,
        // c.update_date, month, u.email) FROM CHECK_NOON C JOIN TIME_LINE L ON
        // C.ID_TIME_LINE = L.ID \n"
        // + //
        // "Join User u on u.id = c.id_user WHERE C.CREATE_BY =:userid and L.MONTH
        // =:timeLine", nativeQuery = true)
        // public List<CheckNoonResponsive>
        // findByUserIdCreateAndTimeLineDto(@Param("userid") String userid,
        // @Param("timeLine") String timeLine);

        @Query(value = "SELECT  new com.vinschool.smarttime.model.response.CheckNoonResponsive(C.Id, C.date_work, c.id_user_trans, c.is_check, c.Ma_ca,c.name_ca, note, u.full_name, \n"
                        + //
                        "id_user, id_time_line, create_by,c.create_date, is_active,c.update_by, c.update_date, month, u.email)  FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID \n"
                        + //
                        "Join User u on u.id = c.id_user WHERE C.CREATE_BY =:userid and L.MONTH =:timeLine", nativeQuery = true)
        public List<CheckNoonResponsive> findByUserIdWorkAndTimeLineDto(@Param("userid") String userid,
                        @Param("timeLine") String timeLine);

        @Query(value = "SELECT  new com.vinschool.smarttime.model.response.CheckNoonResponsive(C.Id, C.date_work, c.id_user_trans, c.is_check, c.Ma_ca,c.name_ca, note, u.full_name, \n"
                        + //
                        "id_user, id_time_line, create_by,c.create_date, is_active,c.update_by, c.update_date, month, u.email)  FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID \n"
                        + //
                        "Join User u on u.id = c.id_user WHERE C.CREATE_BY =:userid and L.MONTH =:timeLine", nativeQuery = true)
        public List<CheckNoonResponsive> findByUserIdCreateAndTimeLineDto(@Param("userid") String userid,
                        @Param("timeLine") String timeLine);

        @Modifying
        @Transactional
        @Query(value = "SELECT C.Id, C.date_work, c.id_user_trans, c.is_check, c.Ma_ca,c.name_ca, note, id_user, id_time_line, create_by,create_date, is_active, update_by,update_date, month FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID   WHERE C.ID_USER =:userid ", nativeQuery = true)
        public List<CheckNoon> findByUserIdWork(@Param("userid") String userid);

        @Modifying
        @Transactional
        @Query(value = "SELECT C.Id, C.date_work, c.id_user_trans, c.is_check, c.Ma_ca,c.name_ca, note, id_user, id_time_line, create_by,create_date, is_active,update_by, update_date, month FROM CHECK_NOON C JOIN TIME_LINE L ON C.ID_TIME_LINE = L.ID   WHERE C.CREATE_BY =:userid", nativeQuery = true)
        public List<CheckNoon> findByUserIdCreate(@Param("userid") String userid);

}
