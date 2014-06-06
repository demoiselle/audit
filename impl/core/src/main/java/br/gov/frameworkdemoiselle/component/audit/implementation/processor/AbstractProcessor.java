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
package br.gov.frameworkdemoiselle.component.audit.implementation.processor;

import br.gov.frameworkdemoiselle.component.audit.AuditProcessorException;
import br.gov.frameworkdemoiselle.component.audit.Processor;
import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import br.gov.frameworkdemoiselle.component.audit.implementation.qualifier.AuditProcessorFail;
import br.gov.frameworkdemoiselle.util.Beans;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;

/**
 *
 * @author SERPRO
 *
 */
public abstract class AbstractProcessor implements Processor {

    private final BeanManager beanManager = Beans.getBeanManager();

    /**
     *
     * @param trail
     */
    @Override
    public void execute(Trail trail) {
        trail.setProcessorName(this.getClass().getName());
    }

    /**
     *
     * @param message
     * @param trail
     */
    @SuppressWarnings("serial")
    protected void fail(String message, Trail trail) {
        beanManager.fireEvent(trail, new AnnotationLiteral<AuditProcessorFail>() {});
        Logger.getLogger(AbstractProcessor.class.getName()).log(Level.SEVERE, null, new AuditProcessorException(message, new Exception()));
    }

}
