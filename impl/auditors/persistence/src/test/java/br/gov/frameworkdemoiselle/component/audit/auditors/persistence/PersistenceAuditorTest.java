/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.frameworkdemoiselle.component.audit.auditors.persistence;

import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import java.util.Date;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author 70744416353
 */
@RunWith(DemoiselleRunner.class)
public class PersistenceAuditorTest {

    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    @Inject
    PersistenceAuditor instance;

    /**
     *
     */
    public PersistenceAuditorTest() {
    }

    /**
     *
     */
    @Before
    public void setUp() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of postLoad method, of class PersistenceAuditor.
     */
    @Test
    public void testPostLoad() {
        Trail trail = new Trail("Teste4Aba", "Usuario1", "id", "Perfil4Aba", "Criou", "10.200.255.26", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\":  \"1\", \"nome\": \"Henrique\", \"filho\": \"Rafael\"}", null, null);
        instance.postLoad(trail);
    }

    /**
     * Test of postRemove method, of class PersistenceAuditor.
     */
    @Test
    public void testPostRemove() {
        Trail trail = new Trail("Teste4Aba", "Usuario1", "id", "Perfil4Aba", "Criou", "10.200.255.26", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\":  \"1\", \"nome\": \"Henrique\", \"filho\": \"Rafael\"}", null, null);
        instance.postRemove(trail);
    }

    /**
     * Test of postUpdate method, of class PersistenceAuditor.
     */
    @Test
    public void testPostUpdate() {
        Trail trail = new Trail("Teste4Aba", "Usuario1", "id", "Perfil4Aba", "Criou", "10.200.255.26", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\":  \"1\", \"nome\": \"Henrique\", \"filho\": \"Rafael\"}", null, null);
        instance.postUpdate(trail);

    }

    /**
     * Test of postPersist method, of class PersistenceAuditor.
     */
    @Test
    public void testPostPersist() {
        Trail trail = new Trail("Teste4Aba", "Usuario1", "id", "Perfil4Aba", "Criou", "10.200.255.26", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\":  \"1\", \"nome\": \"Henrique\", \"filho\": \"Rafael\"}", null, null);
        instance.postPersist(trail);

    }

}
