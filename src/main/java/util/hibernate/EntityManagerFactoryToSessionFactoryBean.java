package util.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;

import javax.persistence.PersistenceUnit;
import java.lang.reflect.Proxy;

public class EntityManagerFactoryToSessionFactoryBean implements org.springframework.beans.factory.FactoryBean<SessionFactory> {

    @PersistenceUnit
    protected javax.persistence.EntityManagerFactory entityManagerFactory;

    @Override
    public SessionFactory getObject() {
        SessionFactory sessionFactory =((HibernateEntityManagerFactory)this.entityManagerFactory).getSessionFactory();
        //拦截器对象
        SessionFactoryInvocationHandler handler = new SessionFactoryInvocationHandler(sessionFactory);
        //返回业务对象的代理对象
        return (SessionFactory)Proxy.newProxyInstance(
                sessionFactory.getClass().getClassLoader(),
                sessionFactory.getClass().getInterfaces(),
                handler);
    }

    @Override
    public Class<?> getObjectType() {
        return SessionFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
