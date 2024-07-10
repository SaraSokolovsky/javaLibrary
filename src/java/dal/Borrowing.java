/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author The user
 */
@Entity
public class Borrowing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date date;
    private boolean isReturned;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CASTOMER_ID",referencedColumnName = "id")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID",referencedColumnName = "id")
    private Item item;
    
    public Borrowing() {}
    
    public Borrowing(Date date, Customer customer, Item item) {
        this.date = date;
        this.customer = customer;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean isIsReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    @Override
    public String toString() {
        return "library.Borrowing[ id=" + id + " ]";
    }

    public void setReturned(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
