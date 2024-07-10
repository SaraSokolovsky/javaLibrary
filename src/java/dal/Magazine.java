/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author נויגרשל שרה
 */
@Entity
 @DiscriminatorValue("magazine")  
public class Magazine extends Item{

    public Magazine(Date date, int id, String name) {
        super(id, name);
        this.date = date;
    }

    public Magazine(Date date) {
        this.date = date;
    }
    private Date date;

    public Magazine() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
