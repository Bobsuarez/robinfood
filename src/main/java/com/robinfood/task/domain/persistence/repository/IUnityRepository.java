/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.robinfood.task.domain.persistence.repository;

import com.robinfood.task.domain.persistence.entity.ProfessorEntity;
import com.robinfood.task.domain.persistence.entity.UnityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BobSuarez
 */
@Repository
public interface IUnityRepository extends JpaRepository<UnityEntity, Integer>
{
  UnityEntity findByFkProfessorAndInitialAndStatus(ProfessorEntity idProfessor, String Initial, String Status);
}
