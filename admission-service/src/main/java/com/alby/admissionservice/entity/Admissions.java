package com.alby.admissionservice.entity;

import java.time.Instant;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admissions")
@EntityListeners({
    AuditingEntityListener.class
})
public class Admissions {
    
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admissions_id_seq"
    )
    @SequenceGenerator(
            name = "admissions_id_seq",
            sequenceName = "admissions_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Default
    private String status = "Y";

    @Column(name = "created_by")
    private String createdBy;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "modified_by")
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_at")
    private Instant modifiedAt;

    @OneToOne
    private Patients patient;
}
