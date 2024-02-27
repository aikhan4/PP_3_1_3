//package com.example.Test_PP_3_1_2.initialization;
//
//import com.example.Test_PP_3_1_2.dao.UserRepository;
//import com.example.Test_PP_3_1_2.models.Role;
//import com.example.Test_PP_3_1_2.models.User;
//import com.example.Test_PP_3_1_2.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class AppInit implements CommandLineRunner {
//    private UserService userService;
//    private ApplicationContext applicationContext;
//    private EntityManager entityManager;
//
//    public AppInit(@Autowired UserService userService, @Autowired ApplicationContext applicationContext, @Autowired EntityManager entityManager) {
//        this.userService = userService;
//        this.applicationContext = applicationContext;
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//            User user = new User("ayhan", "kasumov",  (long) 18, "akasum99@gmail.com", "123");
//            Role roleAdmin = new Role("ROLE_ADMIN");
//            Role roleUser = new Role("ROLE_USER");
//            user.addRole(roleAdmin);
//            user.addRole(roleUser);
//            roleAdmin.addUser(user);
//            roleUser.addUser(user);
//            userService.save(user);
//    }
//}
