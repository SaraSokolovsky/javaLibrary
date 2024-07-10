/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import dal.Item;
import dal.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author The user
 */
public class ItemService {

    Transaction trx = null;
    Session session;

    public void addItem(Item item) {
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            trx = session.beginTransaction();
            session.save(item);
            trx.commit();

        } catch (Exception e) {
            trx.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Item findItemById(int id) {
        Item item1 = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            item1 = (Item) session.get(Item.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return item1;

        }

    }

}
