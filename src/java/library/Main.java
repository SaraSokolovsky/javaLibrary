/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import bl.CustomerService;
import bl.ItemService;
import dal.Book;
import dal.Category;
import dal.Customer;
import dal.Item;
import dal.Magazine;
import dal.NewHibernateUtil;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author נויגרשל שרה
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Transaction trx=null;
//        Customer c=new Customer("Leah","777");
//        try{
//            Session session=NewHibernateUtil.getSessionFactory().openSession();
//            trx=session.beginTransaction();
//            session.save(c);
//            Customer c1;
//            c1 = (Customer)session.get(Customer.class, 1);
//            System.out.println(c1.toString());
//            Item i=new Book(1,"istarak","mayah",Category.ADULTS);
//            Book b=new Book(1,"mallalel","mayah",Category.ADULTS);
//            Magazine m=new Magazine(new Date(), 5, "zman");
//            session.save(i);
//            session.save(b);
//            session.save(m);
//            Query q=session.getNamedQuery("book.getAll");
//            Query q2=session.createQuery("from Book where id=:bookid");
//            q2.setInteger("bookid", 1);
//            q2.list().stream().forEach(System.out::println);
//            q.list().stream().forEach(System.out::println);
//            trx.commit();
//        }
//        catch(Exception e){
//            trx.rollback();
//        }
//        CustomerService cs=new CustomerService();
//        cs.addClient(new Customer("Moshe","lll"));
//        cs.addClient(new Customer("Michael","99"));
//        cs.addClient(new Customer("Yossi","kkk"));
//        cs.addClient(new Customer("Avraham","000"));
//        cs.addClient(new Customer("Elchanan","00000"));
//        System.out.println(cs.findClientById(2));
//        System.out.println(cs.findClientById(4));
//        ItemService i=new ItemService();
//        i.addItem(new Item(1,"Istarak"));
//        
    }
    
}
