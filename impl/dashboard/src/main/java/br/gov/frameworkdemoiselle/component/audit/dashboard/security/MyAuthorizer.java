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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.component.audit.dashboard.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.security.AuthenticationException;
import br.gov.frameworkdemoiselle.security.Authorizer;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

/**
 * 
 * @author SERPRO
 * 
 */
public class MyAuthorizer implements Authorizer {

    private static final long serialVersionUID = 9096827023234484L;

    @Inject
    private Identity identity;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private ResourceBundle rb;

    /**
     *
     * @param role
     * @return
     */
    @Override
    public boolean hasRole(String role) {
        try {
        	return role.equals(identity.getPapel());
        }
        catch (Exception ex) {
            throw new AuthenticationException(rb.getString("controle.acesso.tem.papel.excecao"), ex);
        }
    }

    /**
     *
     * @param resource
     * @param operation
     * @return
     */
    @Override
    public boolean hasPermission(String resource, String operation) {
        try {

        	Map<Integer, Integer> recursoOperacoes = identity.getRecursosOperacoes();

        	List<String> operacoes = new ArrayList<String>();
        	operacoes.add(operation);

        	Integer recurso = Resources.getResource(resource);
        	Integer operacao = Operations.getOperation(operacoes);

        	for (Map.Entry<Integer, Integer> entry : recursoOperacoes.entrySet()){
        		if(recurso.equals(entry.getKey()) && operacao.equals(entry.getValue())){
        			return true;
        		}
        	}

        	return false;

        } catch (Exception ex) {
            throw new AuthenticationException(rb.getString("controle.acesso.tem.permissao.excecao"), ex);
        }

    }
}
