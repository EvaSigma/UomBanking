package org.example;

import model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ClientDB{

    //  Save a Client
    public static void saveClient(Client aClient){
//      Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry reg = standardServiceRegistryBuilder.build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//       Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//       Saving and closing session
        session.save(aClient);
        session.getTransaction().commit();
        session.close();
        System.out.println("Client saved");
    }

    //     Update a Client
    public static void updateClient(Client aClient){

//       Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database

//       Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//       Saving and closing session
        session.saveOrUpdate(aClient);
        session.getTransaction().commit();
        session.close();
        System.out.println("Client updated");
    }

    //      Fetch a Client
    public static Client fetchClient(String ID){
//       Setting up the transaction between the app and the database
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addResource("mapping.hbm.xml");
        StandardServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession(); //using the session to fetch data from the database
//       Beginning the transaction with the database
        Transaction tx = session.beginTransaction();

//       Fetching  client
        Client aClient = new Client();
        aClient = (Client) session.get(Client.class, ID);

//       Saving and closing session
        session.getTransaction().commit();
        session.close();
        return aClient;
    }
}
