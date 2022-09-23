/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.robinfood.task.domain.persistence.repository;

import com.robinfood.task.domain.persistence.entity.InfoPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BobSuarez
 */
@Repository
public interface IInfoPersonRepository extends JpaRepository<InfoPersonEntity, Integer>
{

}
