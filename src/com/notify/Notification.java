
package com.notify;

import com.mail.Mail;
import com.model.Customer;
import com.persistence.PersistCustomer;
import java.sql.SQLException;
import java.util.Iterator;
import javafx.collections.ObservableList;


public class Notification {
    public static void notify(String subject, String message) throws SQLException
    {
        Customer customer;
        ObservableList<Customer> customerList = PersistCustomer.retrieveAll();
        Iterator<Customer> it = customerList.iterator();
        while(it.hasNext())
        {
            customer = it.next();
            Mail.sendNotification(customer, subject, message);
        }
    }
}
