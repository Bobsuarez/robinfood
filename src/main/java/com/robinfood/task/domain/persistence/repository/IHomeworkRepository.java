/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.robinfood.task.domain.persistence.repository;

import com.robinfood.task.domain.persistence.entity.HomeworkEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BobSuarez
 */
@Repository
public interface IHomeworkRepository extends JpaRepository<HomeworkEntity, Integer>
{

  HomeworkEntity findByFileNameAndStatus(String title, String Status);

  @Query(value = """
              SELECT h.*, nt.*
                FROM college.homework h
                         INNER JOIN college.student s ON h.fk_student = s.code
                         INNER JOIN college.info_person ip ON s.fk_infoperson = ip.code
                         LEFT JOIN college.note nt ON h.code = nt.fk_homework
                WHERE ip.identification ILIKE :identification
                  AND CAST(h.instant AS DATE) BETWEEN :date_initial AND :date_final
                ORDER BY h.instant DESC;
                 """, nativeQuery = true)
  List<HomeworkEntity> findByTaskList(
          @Param("identification") String identification,
          @Param("date_initial") LocalDate dateInitial,
          @Param("date_final") LocalDate dateFinal);
}
