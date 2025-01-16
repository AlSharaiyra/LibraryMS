package org.LibraryMS.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;

import java.util.HashMap;
import java.util.Map;

public class JPAUtil {
    private static EntityManagerFactory emf;

    static {
        try {
//            Map<String, String> configOverrides = new HashMap<>();
//            configOverrides.put("jakarta.persistence.jdbc.url", System.getenv("DB_URL"));
//            configOverrides.put("jakarta.persistence.jdbc.user", System.getenv("DB_USER"));
//            configOverrides.put("jakarta.persistence.jdbc.password", System.getenv("DB_PASSWORD"));
//            configOverrides.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");

            Map<String, String> configOverrides = new HashMap<>();
            configOverrides.put("jakarta.persistence.jdbc.url", "jdbc:mysql://localhost:3306/library");
            configOverrides.put("jakarta.persistence.jdbc.user", "root");
            configOverrides.put("jakarta.persistence.jdbc.password", "cyberghost23@mysql");
            configOverrides.put("jakarta.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");

            emf = Persistence.createEntityManagerFactory("LibraryMS", configOverrides);
        } catch (PersistenceException e) {
            System.err.println("Failed to initialize JPA EntityManagerFactory. Check environment variables.");
            e.printStackTrace();
            throw e;
        }
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (emf != null) {
            emf.close();
        }
    }
}
