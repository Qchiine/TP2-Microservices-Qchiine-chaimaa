package outils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import metier.IMetier;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import presentation.ContexteSpring;

/**
 * Verifie que l'instrumentation ajoutee fonctionne sans modifier le code metier.
 */
public class PostProcesseurTraceTest {

    @Test
    public void lesAppelsMetierSontJournalises() {
        try (AnnotationConfigApplicationContext contexte = ContexteSpring.demarrer("dev")) {
            JournalExecution journal = ContexteSpring.journal(contexte);

            IMetier metier = contexte.getBean("metier", IMetier.class);
            double resultat = metier.calcul();

            assertEquals(300.0, resultat, 0.001);
            assertEquals(1, journal.taille());
            assertEquals("calcul", journal.getEntrees().get(0).getMethode());
            assertTrue(journal.getEntrees().get(0).getCible().contains("qualifie"));
        }
    }

    @Test
    public void unBeanSansAnnotationNestPasInstrumente() {
        try (AnnotationConfigApplicationContext contexte = ContexteSpring.demarrer("prod")) {
            JournalExecution journal = ContexteSpring.journal(contexte);

            contexte.getBean("dao", dao.IDao.class).getValue();

            assertFalse(journal.taille() > 0);
        }
    }
}