package com.devskiller.tasks.blog.repository;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Rate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {

	Comment findByTimeStamp(long postId);
}
