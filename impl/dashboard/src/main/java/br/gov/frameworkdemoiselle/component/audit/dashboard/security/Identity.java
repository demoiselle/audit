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
package br.gov.frameworkdemoiselle.component.audit.dashboard.security;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.frameworkdemoiselle.message.MessageContext;
import br.gov.frameworkdemoiselle.message.SeverityType;
import br.gov.frameworkdemoiselle.security.SecurityContext;

/**
 * 
 * @author SERPRO
 * 
 */
@Named
@SessionScoped
public class Identity implements Serializable, Principal {

    private static final long serialVersionUID = 8003651916557123604L;
    private Boolean isLogged = false;
    private Long id;
    private String name;
    private String email;
    private String usuario;
    private String password;
    private Long orgao;
    private String nomeorgao;
    private String papel;
    private Boolean lembreMeCookie;

    @Inject
    private FacesContext facesContext;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private MessageContext messageContext;
	private Map<Integer, Integer> recursosOperacoes;

    public Identity(){


    }

    /**
     *
     * @return
     */
    public String login() {
        try {
            securityContext.login();

            return "";
        }
        catch (Exception e) {
            messageContext.add(e.getMessage(), SeverityType.ERROR);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String logout() {
        try {
            securityContext.logout();
            return "index.jsf";
        } catch (Exception e) {
            messageContext.add(e.getMessage(), SeverityType.ERROR);
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public Boolean getIsLogged() {
        return isLogged;
    }

    /**
     *
     * @param isLogged
     */
    public void setIsLogged(Boolean isLogged) {
        this.isLogged = isLogged;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Long getOrgao() {
        return orgao;
    }

    /**
     *
     * @param orgao
     */
    public void setOrgao(Long orgao) {
        this.orgao = orgao;
    }

    /**
     *
     * @return
     */
    public String getPapel() {
        return papel;
    }

    /**
     *
     * @param papel
     */
    public void setPapel(String papel) {
        this.papel = papel;
    }

    /**
     *
     * @return
     */
    public String getNomeorgao() {
        return nomeorgao;
    }

    /**
     *
     * @param nomeorgao
     */
    public void setNomeorgao(String nomeorgao) {
        this.nomeorgao = nomeorgao;
    }

    /**
     *
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }


	public Map<Integer, Integer> getRecursosOperacoes() {
		return recursosOperacoes;
	}

	public void setRecursosOperacoes(Map<Integer, Integer> recursosOperacoes) {
		this.recursosOperacoes = recursosOperacoes;
	}

	public Boolean getLembreMeCookie() {
		return lembreMeCookie;
	}

	public void setLembreMeCookie(Boolean lembreMeCookie) {
		this.lembreMeCookie = lembreMeCookie;
	}
}
