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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.Usuario;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.UsuarioRecurso;
import br.gov.frameworkdemoiselle.component.audit.dashboard.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.component.audit.dashboard.persistence.UsuarioRecursoDAO;
import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authenticator;
import br.gov.frameworkdemoiselle.security.User;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

/**
 * 
 * @author SERPRO
 * 
 */
public class MyAuthenticator implements Authenticator {

    private static final long serialVersionUID = 5348324948048837944L;

    @Inject
    private User identity;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private UsuarioRecursoDAO usuarioRecursoDAO;

    @Inject
    private ResourceBundle rb;

    /**
     *
     * @throws AuthenticationException
     */
    @SuppressWarnings("unused")
    @Override
    public void authenticate() throws AuthenticationException {
        Usuario user;
        Map<Integer, Integer> recursosOperacoes = new HashMap<Integer, Integer>();

        try {
            user = usuarioDAO.findById(Long.getLong(identity.getId()));
            
            if(user != null){

                List<UsuarioRecurso> recursosUsuario = usuarioRecursoDAO.findByUsuario(user.getId());

                if(recursosUsuario != null){

                    Iterator<UsuarioRecurso> it = recursosUsuario.iterator();

                    while (it.hasNext()) {
                        UsuarioRecurso usuarioRecurso = it.next();
                        recursosOperacoes.put(usuarioRecurso.getUsuarioRecursosPK().getRecursos(), usuarioRecurso.getOperacao());
                    }
                }
                else{
                    throw new AuthenticationException(rb.getString("login.usuario.nao.existe"), new Exception());   
                }
            }
            else{
                throw new AuthenticationException(rb.getString("login.usuario.nao.existe"), new Exception());   
            }

        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("login.usuario.nao.existe"), ex);
        }

        
        if (!user.getAminesia().isEmpty() && user.getSenha().equals(user.getAminesia().substring(21, 27))) {
            throw new AuthenticationException(rb.getString("login.alteracao.por.email"));
        }

        this.identity.setAttribute("Nome", user.getNome());
        this.identity.setAttribute("Papel", Roles.getRole(user.getPapel()).get(0));
        this.identity.setAttribute("Recurso", recursosOperacoes);
    }

    /**
     *
     * @throws java.lang.Exception
     */
    @Override
    public void unauthenticate() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User getUser() {

        if (this.identity != null && this.identity.getId() != null) {
            return this.identity;
        } else {
            return null;
        }
    }

}
