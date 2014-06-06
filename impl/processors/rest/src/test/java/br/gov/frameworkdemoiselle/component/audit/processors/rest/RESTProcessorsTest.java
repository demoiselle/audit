/*
 * Demoiselle Framework
 * Copyright (C) 2014 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */
package br.gov.frameworkdemoiselle.component.audit.processors.rest;

import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import br.gov.frameworkdemoiselle.junit.DemoiselleRunner;
import java.util.Date;
import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author SERPRO
 *
 */
@RunWith(DemoiselleRunner.class)
public class RESTProcessorsTest {

    @Inject
    private RESTProcessors instance;

    /**
     *
     */
    public RESTProcessorsTest() {
    }

    /**
     * Test of create method, of class TrilhaConsumes.
     * @throws java.lang.Exception
     */
    @Test
    public void testExecute() throws Exception {

        instance.execute(new Trail("Teste4Aba", "Usuario1", "id", "Perfil4Aba", "Criou", "10.200.255.26", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\":  \"1\", \"nome\": \"Henrique\", \"filho\": \"Rafael\"}", null, null));
        instance.execute(new Trail("Teste4Aba", "Usuario2", "id", "Perfil4Aba", "Alterou", "10.200.255.37", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"Rafael\", \"filho\": \"José\"}", null, null));
        instance.execute(new Trail("Teste4Aba", "Usuario3", "id", "Perfil4Aba", "Alterou", "10.200.255.29", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"José\", \"filho\": \"Henrique\"}", null, null));
        instance.execute(new Trail("Teste4Aba", "Usuario4", "id", "Perfil4Aba", "Alterou", "10.200.255.44", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"Roberto\", \"filho\": \"Silva\"}", null, null));
        instance.execute(new Trail("Teste4Aba", "Usuario5", "id", "Perfil4Aba", "Excluiu", "10.200.255.73", new Date(), "br.gov.frameworkdemoiselle.serpro.teste4aba", "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"Roberto\", \"filho\": \"Silva\"}", null, null));

    }

}
