package com.cherylorcutt.spring5webapp.repositories;

import org.springframework.data.repository.CrudRepository;
import com.cherylorcutt.spring5webapp.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
