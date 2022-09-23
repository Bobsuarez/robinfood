/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.robinfood.task.domain.persistence.repository;

import com.robinfood.task.domain.persistence.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BobSuarez
 */
@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, Integer>
{

  @Query(value = """
                 SELECT s.*
                 FROM college.student s
                          INNER JOIN college.info_person ip ON ip.code = s.fk_infoperson
                 WHERE ip.identification ILIKE :identification
                 """, nativeQuery = true)
  StudentEntity findByIdentification(
          @Param("identification") String identification);

}
