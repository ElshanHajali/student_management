package com.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ApiModel(description = "Student entity")
public class Student {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Student Id generating automatically...")
    private Long id;

    @ApiModelProperty(notes = "Student name.")
    private String name;

    @ApiModelProperty(notes = "Student phone number.")
    private String phone;

}
