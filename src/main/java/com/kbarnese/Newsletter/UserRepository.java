package com.kbarnese.Newsletter;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>, FindByEmail<User, String> {
    boolean existsUserByEmail(String email);
}
