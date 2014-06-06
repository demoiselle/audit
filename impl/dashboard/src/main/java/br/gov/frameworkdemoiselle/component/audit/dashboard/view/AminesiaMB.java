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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.component.audit.dashboard.business.SecurityBC;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.Usuario;
import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractEditPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;

/**
 *
 * @author SERPRO
 *
 */
@ViewController
@NextView("./index.jsf")
@PreviousView("./login.jsf")
public class AminesiaMB extends AbstractEditPageBean<Usuario, Long> {

    private static final long serialVersionUID = 1L;
    @Inject
    private SecurityBC securityBC;
    @Inject
    private MessageContext messageContext;
    private String email;
    private String aminesia;
    private String senhaatual;
    private String senhanova;
    private String senharepetida;

    public AminesiaMB() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest) ctx.getExternalContext().getRequest();
        aminesia = req.getParameter("quem");
    }

    /**
     *
     * @return
     */
    @Override
    @Transactional
    public String insert() {
        return null;
    }

    public String lembrar() {
        try {
            securityBC.enviarMensagemLembrandoSenha(email);
            messageContext.add("{aminesia.mensagem.lembrete.enviado}", SeverityType.INFO);
        } catch (Exception ex) {
            messageContext.add("{aminesia.lembrete.email.nao.cadastrado}", SeverityType.ERROR);
            return null;
        }
        return getPreviousView();
    }

    /**
     *
     * @return
     */
    @Override
    @Transactional
    public String update() {
        try {
            if (aminesia == null) {
                return getPreviousView();
            }
            if (senhanova.equals(senharepetida)) {
                this.securityBC.alteraSenha(aminesia, senhanova);
            } else {
                messageContext.add("{aminesia.nova.senha.diferente}", SeverityType.ERROR);
                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(AminesiaMB.class.getName()).log(Level.SEVERE, null, ex);
            messageContext.add(ex.getMessage(), SeverityType.ERROR);
            return null;
        }
        return getNextView();
    }

    /**
     *
     * @return
     */
    @Override
    public String delete() {
        return null;
    }

    /**
     *
     * @return
     */
    public String getAminesia() {
        return aminesia;
    }

    /**
     *
     * @param aminesia
     */
    public void setAminesia(String aminesia) {
        this.aminesia = aminesia;
    }

    /**
     *
     * @return
     */
    public String getSenhaatual() {
        return senhaatual;
    }

    /**
     *
     * @param senhaatual
     */
    public void setSenhaatual(String senhaatual) {
        this.senhaatual = senhaatual;
    }

    /**
     *
     * @return
     */
    public String getSenhanova() {
        return senhanova;
    }

    /**
     *
     * @param senhanova
     */
    public void setSenhanova(String senhanova) {
        this.senhanova = senhanova;
    }

    /**
     *
     * @return
     */
    public String getSenharepetida() {
        return senharepetida;
    }

    /**
     *
     * @param senharepetida
     */
    public void setSenharepetida(String senharepetida) {
        this.senharepetida = senharepetida;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    protected Usuario handleLoad(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
