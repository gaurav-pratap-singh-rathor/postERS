package com.gaurav.rest.webservices.postERS.jpa;

import com.gaurav.rest.webservices.postERS.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


// IT is UserDaoService(JPA)

/* At the time Static data we are using userDaoService and created our own methods like findbyid and deletebyid
 But when using JPA we will create this interface and extend the JpaRepository which already have all that methods findByID , delete, retrive and all
 so we just create the userRepository object in UserJpaResource which of type this interface and using that object we can retrive all inbuilt methods in JpaRepository.
*/
public interface UserRepository extends JpaRepository<User, Integer> {

}
