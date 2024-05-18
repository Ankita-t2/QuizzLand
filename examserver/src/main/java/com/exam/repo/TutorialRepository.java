package com.exam.repo;

import com.exam.model.exam.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial,Long> {

    public  Tutorial findByTutId(long tutID);


}
