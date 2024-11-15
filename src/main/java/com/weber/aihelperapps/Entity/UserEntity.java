package com.weber.aihelperapps.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private Long tgUserId;

    @ManyToOne
    private GroupEntity group;
}
