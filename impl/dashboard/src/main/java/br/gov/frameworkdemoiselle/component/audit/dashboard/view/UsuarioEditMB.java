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
package br.gov.frameworkdemoiselle.component.audit.dashboard.view;


import javax.inject.Inject;

import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.component.audit.dashboard.business.SecurityBC;
import br.gov.frameworkdemoiselle.component.audit.dashboard.business.UsuarioBC;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.Usuario;
import br.gov.frameworkdemoiselle.component.audit.dashboard.security.Roles;
import br.gov.frameworkdemoiselle.exception.ExceptionHandler;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import java.util.Map;

/**
 *
 * @author SERPRO
 *
 */
@ViewController
@PreviousView("./usuario_list.jsf")
public class UsuarioEditMB extends AbstractEditPageBean<Usuario, Long> {

    private static final long serialVersionUID = 1L;

    @Inject
    private MessageContext messageContext;

    @Inject
    private UsuarioBC usuarioBC;

    @Inject
    private SecurityBC securityBC;

    @Override
    @Transactional
    public String delete() {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public String ativar() {
        getBean().setIsAtivo(Boolean.TRUE);
        this.usuarioBC.update(getBean());

        messageContext.add("{usuario-ativo-ok}");

        return getPreviousView();
    }

    @Transactional
    public String inativar() {
        getBean().setIsAtivo(Boolean.FALSE);
        this.usuarioBC.update(getBean());

        messageContext.add("{usuario-inativo-ok}");

        return getPreviousView();
    }

    @Override
    @Transactional
    public String insert() {
        getBean().setSenha("123");
        getBean().setAminesia("123");
        getBean().setIsAtivo(Boolean.FALSE);

        this.usuarioBC.insert(getBean());

        messageContext.add("{usuario-insert-ok}", getBean().getUsuario());

        try {
            securityBC.enviarMensagemLembrandoSenha(getBean());
        } catch (Exception e) {
            messageContext.add("{email.exception.generico}", e.getMessage());
        }

        return getPreviousView();
    }

    @Override
    @Transactional
    public String update() {
        getBean().setSenha("123");
        getBean().setAminesia("123");
        getBean().setIsAtivo(Boolean.FALSE);

        this.usuarioBC.update(getBean());

        messageContext.add("{usuario-update-ok}", getBean().getUsuario());

        try {
            securityBC.enviarMensagemLembrandoSenha(getBean());
        } catch (Exception e) {
            messageContext.add("{email.exception.generico}", e.getMessage());
        }

        return getPreviousView();
    }

    public Map<Short, String> getPapeis() {
        return Roles.getRolesListAsMap();
    }

    @ExceptionHandler
    private void tratarExcecao(Exception e) {
        messageContext.add("{guddi.erro.generico}", SeverityType.ERROR);
    }

    @Override
    protected Usuario handleLoad(Long id) {
        setBean(this.usuarioBC.load(getId()));
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
