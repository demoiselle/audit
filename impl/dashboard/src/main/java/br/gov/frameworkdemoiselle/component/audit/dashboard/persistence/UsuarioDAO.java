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

import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.Usuario;
import br.gov.frameworkdemoiselle.component.audit.dashboard.util.CriptografiaUtil;
import br.gov.frameworkdemoiselle.stereotype.PersistenceController;
import br.gov.frameworkdemoiselle.template.JPACrud;
import br.gov.frameworkdemoiselle.util.ResourceBundle;

import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * 
 * @author SERPRO
 * 
 */

@PersistenceController
public class UsuarioDAO extends JPACrud<Usuario, Long> {

    private static final long serialVersionUID = 1L;

    @Inject
    private ResourceBundle rb;

    /**
     *
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
    public Usuario findById(Long id) throws Exception{
            return (Usuario) getEntityManager().createNamedQuery("Usuario.findById").setParameter("id", id).getSingleResult();
    }

    public Usuario findByAminesia(String aminesia) throws Exception{
            return (Usuario) getEntityManager().createNamedQuery("Usuario.findByAminesia").setParameter("aminesia", aminesia).getSingleResult();
    }

    public Usuario findByEmail(String email) throws Exception{
            return (Usuario) getEntityManager().createNamedQuery("Usuario.findByEmail").setParameter("email", email).getSingleResult();
    }

    /**
     *
     * @param idUsuario
     * @param idRole
     * @return
     * @throws java.lang.Exception
     */
    public Boolean hasRole(Long idUsuario, Short idRole) throws Exception{
        return (Boolean) getEntityManager().createNativeQuery("SELECT COUNT(1) > 0 FROM guddi.usuario WHERE id = :idUser AND papel = :papel")
                .setParameter("idUser", idUsuario)
                .setParameter("papel", idRole)
                .getSingleResult();
    }

    /**
     *
     * @param idUsuario
     * @param idResource
     * @return
     * @throws java.lang.Exception
     */
    public Integer hasPermission(Long idUsuario, Long idResource) throws Exception{
        return (Integer) getEntityManager().createNativeQuery("SELECT operacao FROM usuario_recursos ur WHERE id_usuario = :idUser AND id_recursos = :resource")
                .setParameter("idUser", idUsuario)
                .setParameter("resource", idResource)
                .getSingleResult();

    }

    /**
     *
     * @param usuario
     * @return
     * @throws java.lang.Exception
     */
    public void updatePassWithAminesia(String aminesia, String senhaNova) throws Exception {
        try {
            Usuario usu = findByAminesia(aminesia);
            if (usu != null) {
                usu.setSenha(CriptografiaUtil.getCodigoMd5(senhaNova));
                update(usu);
            }
            else{
                 throw new Exception(rb.getString("aminesia.senha.atual.invalida"));
            }
        } catch (NoResultException e) {
           throw e;
        }
    }


}
