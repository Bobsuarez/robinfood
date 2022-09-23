/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;

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
@Table(schema = "college", name = "unity")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnityEntity implements Serializable
{

  private static final long serialVersionUID = 8349574768354275455L;

  /**
   * The id.
   */
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Column(name = "code")
  private Integer code;

  @NotFound
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "fk_professor", referencedColumnName = "code")
  private ProfessorEntity fkProfessor;

  @Column(name = "title", length = 40)
  private String title;

  @Column(name = "description", columnDefinition = "Jsonb")
  private String description;

  @Column(name = "initial")
  private String initial;

  @Column(name = "instant", columnDefinition = "Timestamp")
  private LocalDateTime instant;

  @Column(name = "status", length = 2)
  private String status;
}
