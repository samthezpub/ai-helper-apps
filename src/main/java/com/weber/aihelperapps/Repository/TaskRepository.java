package com.weber.aihelperapps.Repository;

import com.weber.aihelperapps.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    @Query("SELECT t FROM TaskEntity t WHERE t.status = 'IN_PROGRESS' AND t.user.tgUserId =:userId")
    List<TaskEntity> getAllInProgressByUserId(@Param("userId") Long userId);
}
