package dao;

import org.hibernate.SessionFactory;

public final class ConexaoHibernate {

    private ConexaoHibernate() {
    }

    public static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }
}
