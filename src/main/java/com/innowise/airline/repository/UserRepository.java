package com.innowise.airline.repository;

import com.innowise.airline.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

//    TODO: почему бы не Deleted и переменная? Ты явно ограничиваешь себе пронстранство для маневра.
//     Что будешь делать, если потребуется только удаленных пользователей достать? Или вообще всех?
    Page<User> findUsersByDeletedFalse(Pageable pageable);
}
