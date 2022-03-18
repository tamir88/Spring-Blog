package com.tamirhamtzany.springblog.repository;

import com.tamirhamtzany.springblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
