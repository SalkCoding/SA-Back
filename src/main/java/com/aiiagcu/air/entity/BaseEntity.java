package com.aiiagcu.air.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    //생성 시간과 업데이트 시간을 알려주는 부모 클래스

    //특징:
    //따로 테이블을 생성하지 않음
    //대신 이 클래스의 필드들은 하위 엔티티에 포함됨

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updatedTime;
}
