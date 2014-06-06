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
package br.gov.frameworkdemoiselle.component.audit.dashboard.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.LocalTrail;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.Usuario;
import br.gov.frameworkdemoiselle.component.audit.dashboard.persistence.UsuarioDAO;
import br.gov.frameworkdemoiselle.component.audit.dashboard.util.CriptografiaUtil;
import br.gov.frameworkdemoiselle.component.audit.dashboard.util.Trololo;
import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.transaction.Transactional;

/**
 *
 * @author SERPRO
 *
 */
@BusinessController
public class SecurityBC implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private static final int SENHA_INICIO = 21;
    private static final int SENHA_FINAL = 27;
    private static final int QUANTIDADE_REGISTROS = 3000;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private TrilhaBC trailBC;

    @Startup
    @Transactional
    public void bootstrap() {
        try {

            List<Long> ids = new ArrayList<Long>();
            for (LocalTrail trail : trailBC.findAll()) {
                ids.add(trail.getIdaudit());
            }

            trailBC.delete(ids);

            Trololo tro = new Trololo();

            for (int i = 0; i < QUANTIDADE_REGISTROS; i++) {
            	trailBC.insert(new LocalTrail(null, tro.getSistema(), tro.getUsuario(), "id", tro.getPerfil(), tro.getOque(), tro.getOnde(), tro.getQuando(),"br.gov.frameworkdemoiselle.serpro.audit", tro.getObjetoSerial(), null, null));
            }

            String className = "br.gov.frameworkdemoiselle.serpro.teste4aba";
            String systemName = "Teste4Aba";
            
            trailBC.insert(new LocalTrail(null, systemName, "Usuario1", "id", "Perfil4Aba", "Criou",   "10.200.255.26", new Date(), className, "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"Henrique\", \"filho\": \"Rafael\"}", null, null));
            trailBC.insert(new LocalTrail(null, systemName, "Usuario2", "id", "Perfil4Aba", "Alterou", "10.200.255.37", new Date(), className, "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"Rafael\", \"filho\": \"José\"}", null, null));
            trailBC.insert(new LocalTrail(null, systemName, "Usuario3", "id", "Perfil4Aba", "Alterou", "10.200.255.29", new Date(), className, "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"José\", \"filho\": \"Henrique\"}", null, null));
            trailBC.insert(new LocalTrail(null, systemName, "Usuario4", "id", "Perfil4Aba", "Alterou", "10.200.255.44", new Date(), className, "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"Roberto\", \"filho\": \"Silva\"}", null, null));
            trailBC.insert(new LocalTrail(null, systemName, "Usuario5", "id", "Perfil4Aba", "Excluiu", "10.200.255.73", new Date(), className, "{\"class\": \"br.gov.serpro.siafi.domain.Trilha\",\"id\": \"1\", \"nome\": \"Roberto\", \"filho\": \"Silva\"}", null, null));

        } catch (Exception e) {
            Logger.getLogger(SecurityBC.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     *
     * @param aminesia
     * @param senhaatual
     * @param senhanova
     * @throws java.lang.Exception
     */
    public void alteraSenha(String aminesia, String senhanova) throws Exception {
        usuarioDAO.updatePassWithAminesia(aminesia, senhanova);
    }

    public void enviarMensagemLembrandoSenha(Usuario usuario) throws Exception {
        enviarMensagemLembrandoSenha(usuario.getEmail());
    }

    public void enviarMensagemLembrandoSenha(String destinatario) throws Exception {
        Usuario usuario = usuarioDAO.findByEmail(destinatario);
        String senha = CriptografiaUtil.getCodigoMd5("" + System.currentTimeMillis());
        usuario.setAminesia(senha);
        usuario.setSenha(senha.substring(SENHA_INICIO, SENHA_FINAL));
        usuarioDAO.update(usuario);

    }
}
