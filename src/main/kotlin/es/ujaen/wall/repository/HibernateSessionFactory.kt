package es.ujaen.wall.repository

import es.ujaen.wall.model.Post
import org.hibernate.SessionFactory
import org.hibernate.cfg.AvailableSettings
import org.hibernate.cfg.Configuration

fun createSessionFactory(): SessionFactory =
    Configuration()
        .addAnnotatedClass(Post::class.java)
        .setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER, "org.h2.Driver")
        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:h2:mem:wall;DB_CLOSE_DELAY=-1")
        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "sa")
        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
        .setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop")
        .buildSessionFactory()
