package com.alby.admissionservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patients")
@EntityListeners({
    AuditingEntityListener.class
})
public class Patients {
    
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patients_id_seq"
    )
    @SequenceGenerator(
            name = "patients_id_seq",
            sequenceName = "patients_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String name;

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
}
