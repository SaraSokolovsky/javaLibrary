/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import dal.Book;
import dal.Borrowing;
import dal.Customer;
import dal.Item;
import dal.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author The user
 */
public class BorrowingService {

    Transaction trx = null;
    Session session;

    public void addBorrowing(Borrowing b) {
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            trx = session.beginTransaction();
            session.save(b);
            trx.commit();
        } catch (Exception e) {
            String err = e.getMessage();
            StackTraceElement[] st = e.getStackTrace();
            System.out.println(err);
            trx.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public boolean returnBook(Item item, Customer customer) {
        Session session = null;
        Transaction trx = null;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            trx = session.beginTransaction();

            String hql = "FROM Borrowing WHERE item.id = :itemId AND customer.id = :customerId AND isReturned = false";
            Query query = session.createQuery(hql);
            query.setParameter("itemId", item.getId());
            query.setParameter("customerId", customer.getId());

            Borrowing borrowingToUpdate = (Borrowing) query.uniqueResult();

            if (borrowingToUpdate == null) {
                // No active borrowing found, return false
                return false;
            }

            borrowingToUpdate.setReturned(true);
            session.update(borrowingToUpdate);
            trx.commit();

            return true; // Successfully updated borrowing

        } catch (Exception e) {
            if (trx != null) {
                trx.rollback();
            }
            e.printStackTrace();
            return false; // Handle any exception and return false
        } finally {
            if (session != null && session.isOpen()) {
                session.close(); // Always close session in finally block
            }
        }
    }

}
