/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

/**
 *
 * @author נויגרשל שרה
 */
@Entity
@NamedQueries({
@NamedQuery(name = "book.getAll",query = "from Book"),
@NamedQuery(name = "book.getById",query = "from Book where id=:bookid")})
@DiscriminatorValue("book")  
public class Book extends Item{
    private String author;
    private Category category;
 
    

    public Book( int id, String name,String author, Category category) {
        super(id, name);
        this.author = author;
        this.category = category;
    }

    public Book() {
       
    }

    @Override
    public String toString() {
        return super.toString()+"Book{" + "author=" + author + ", category=" + category + '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
