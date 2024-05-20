package com.firstproject.API.MYSQL.Repository;




import com.firstproject.API.MYSQL.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

