/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author bobsuarez
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(schema = "college", name = "info_person")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoPersonEntity implements Serializable
{

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 7238454768232175455L;
  /**
   * The id.
   */
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Column(name = "code")
  private Integer code;

  @Column(name = "name", length = 40)
  private String name;

  @Column(name = "lastname", length = 40)
  private String lastName;

  @Column(name = "identification")
  private String identification;

  @Column(name = "instant", columnDefinition = "Timestamp")
  private LocalDateTime instant;
}
