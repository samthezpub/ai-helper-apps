package com.weber.aihelperapps.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GroupEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String chatId;
    private String chatName;
}
