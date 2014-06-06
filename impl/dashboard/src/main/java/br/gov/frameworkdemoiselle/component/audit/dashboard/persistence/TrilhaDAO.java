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
package br.gov.frameworkdemoiselle.component.audit.dashboard.persistence;

import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.LocalTrail;
import java.util.Date;
import java.util.List;

import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;

/**
 *
 * @author SERPRO
 *
 */
@PersistenceController
public class TrilhaDAO extends JPACrud<LocalTrail, Long> {

    private static final long serialVersionUID = 1L;

    /**
     *
     * @param namedQuery
     * @param param
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<LocalTrail> findByNamedQuery(String namedQuery, String param, String value) {
        return getEntityManager().createNamedQuery(namedQuery).setParameter(param, value).getResultList();
    }

    /**
     *
     * @param namedQuery
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<String> findByNamedQueryDistinct(String namedQuery) {
        return getEntityManager().createNamedQuery(namedQuery).getResultList();
    }

    /**
     *
     * @param namedQuery
     * @param param
     * @param value
     * @param dateBegin
     * @param dateFinal
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<LocalTrail> findByNamedQueryWithBetween(String namedQuery, String param, String value, Date dateBegin, Date dateFinal) {
        return getEntityManager().createNamedQuery(namedQuery).
                setParameter(param, value).
                setParameter("whenBegin", dateBegin).
                setParameter("whenFinal", dateFinal).
                getResultList();
    }

    public List<String> findByNamedQueryDistinct(String namedQuery, String param, String value) {
        return getEntityManager().createNamedQuery(namedQuery).setParameter(param, value).getResultList();
    }

    public String findByNamedQueryIdName(String namedQuery, String systemName, String className) {
        List<String> idNames = (List<String>) getEntityManager().createNamedQuery(namedQuery).
                setParameter("systemName", systemName).
                setParameter("className", className).
                getResultList();

        if (idNames != null && idNames.size() > 0) {
            return idNames.get(0);
        }

        return "";
    }

    public List<LocalTrail> findByNamedQuerySystemAndObjectAndIdName(String namedQuery, String system, String object,
            String objectIdName, String objectIdValue) {

        return getEntityManager().createNamedQuery(namedQuery).
                setParameter("systemName", system).
                setParameter("className", object).
                setParameter("value", "%\"" + objectIdName + "\":" + objectIdValue + "%").
                getResultList();
    }
}
