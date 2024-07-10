/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author נויגרשל שרה
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private int id;
    private String name;
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="ID")
    private List<Borrowing> borrowings;

    public String getName() {
        return name;
    }

    public Item(int id, String name) {
        this();
        this.id = id;
        this.name = name;
    }

    public Item() {
        this.borrowings=new ArrayList<Borrowing>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return "library.Item[ id=" + id +" name: "+name+" Borrowings: "+borrowings+" ]";
    }
    
}
