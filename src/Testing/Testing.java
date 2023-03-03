/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import entite.User;
import service.ServicePersonne;

/**
 *
 * @author Rebhy
 */
public class Testing {
    
        public static void main(String[] args) {
        // TODO code application logic here
//        DataSource ds1 =DataSource.getInstance();
//        System.out.println(ds1);
//        DataSource ds2 = DataSource.getInstance();
//        System.out.println(ds2);
//        DataSource ds3 = DataSource.getInstance();
//        System.out.println(ds3);

     

User newUser = new User("John", "rebhi", 123456789, "johndofde@example.com", "admin", "password123");
ServicePersonne service = new ServicePersonne();
service.insertPst(newUser);
service.delete(newUser);


    }
}
