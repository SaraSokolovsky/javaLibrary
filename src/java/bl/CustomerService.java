/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import dal.Customer;
import dal.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author user
 */
public class CustomerService {
    Transaction trx=null;
    Session session;
   public void addClient(Customer c) {
    Session session = null;
    Transaction trx = null;
    
    try {
        session = NewHibernateUtil.getSessionFactory().openSession();
        
        // Check if customer already exists
        Customer existingCustomer = getCustomerByName(c.getName());
        
        if (existingCustomer != null) {
            System.out.println("Customer with name " + c.getName() + " already exists. Not adding again.");
            return; // Exit without adding
        }
        
        trx = session.beginTransaction();
        session.save(c);
        trx.commit();
    } catch (Exception e) {
        if (trx != null) {
            trx.rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}

    public Customer findClientById(int id){
        Customer c1=null;
        try{
            session=NewHibernateUtil.getSessionFactory().openSession();
            c1 = (Customer)session.get(Customer.class, id);
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return c1;
        }
         
    }
    public Customer getCustomerByName(String name){
        Customer c1=null;
        try{
            session=NewHibernateUtil.getSessionFactory().openSession();
            String hql = "FROM Customer WHERE name = :customerName";
            Query query = session.createQuery(hql);
            query.setParameter("customerName", name);
            c1= (Customer) query.uniqueResult();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return c1;
        }
    }
    public boolean isNameExist(String name){
        Customer c1=this.getCustomerByName(name);
        
        if(c1!=null){
            return true;
        }
        return false;
    }
    public boolean isPasswordCorrect(String name,String password){
        Customer c1=this.getCustomerByName(name);
        if (c1.getPassword().equals(password)){
            return true;
        }
        return false;
    }
    
}

