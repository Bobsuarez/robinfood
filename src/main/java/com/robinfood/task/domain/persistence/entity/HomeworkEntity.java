/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.robinfood.task.domain.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(schema = "college", name = "homework")
@JsonIgnoreProperties(ignoreUnknown = true)
public class HomeworkEntity implements Serializable
{

  private static final long serialVersionUID = 8349574768343175455L;

  /**
   * The id.
   */
  @Id
  @Basic(optional = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Column(name = "code")
  private Integer code;

  @Column(name = "file_name", length = 40)
  private String fileName;

  @Column(name = "instant", columnDefinition = "Timestamp")
  private LocalDateTime instant;

  @Column(name = "status", length = 2)
  private String status;

  @NotFound
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinColumn(name = "fk_student", referencedColumnName = "code")
  private StudentEntity fkStudent;

  @OneToMany(mappedBy = "fkHomework", fetch = FetchType.LAZY)
  private List<NoteEntity> noteEntity;

}
