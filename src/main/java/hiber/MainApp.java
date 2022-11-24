package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Иван", "Иванов", "ivan@mail.ru");
      User user2 = new User("Рома", "Романов", "roma@mail.ru");
      User user3 = new User("Сёма", "Семёнов", "sema@mail.ru");
      User user4 = new User("Дима", "Дмитров", "dima@mail.ru");

      Car car1 = new Car("Porsche", 911);
      Car car2 = new Car("Volvo", 55);
      Car car3 = new Car("Audi", 5);
      Car car4 = new Car("BMW", 535);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println("--------------------------------------------\n");

      }

      System.out.println(userService.getUserByCar("Volvo", 55));


      context.close();
   }
}
