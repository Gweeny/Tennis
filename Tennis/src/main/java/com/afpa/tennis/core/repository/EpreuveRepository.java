package com.afpa.tennis.core.repository;

import com.afpa.tennis.core.HibernateUtil;
import com.afpa.tennis.core.entity.Epreuve;
import org.hibernate.Session;

public class EpreuveRepository {
    public Epreuve getById(Long id) {
        Epreuve epreuve = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        epreuve = session.get(Epreuve.class, id);

        System.out.println("Epreuve lue.");

        return epreuve;
    }
}
