package com.springCloudAd.entity.unit_condition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creative_unit")
public class CreativeUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "create_id",nullable = false)
    private Long createId;

    @Column(name = "unit_id",nullable = false)
    private Long unitId;

    public CreativeUnit(Long createId,Long unitId){
        this.unitId=unitId;
        this.createId=createId;
    }

}
