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
package br.gov.frameworkdemoiselle.component.audit.dashboard.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author SERPRO
 * 
 */
@Entity
@Table(name = "usuario_recursos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioRecurso.findAll", query = "SELECT u FROM UsuarioRecurso u"),
    @NamedQuery(name = "UsuarioRecurso.findByIdUsuario", query = "SELECT u FROM UsuarioRecurso u WHERE u.usuarioRecursoPK.idUsuario = :idUsuario"),
    @NamedQuery(name = "UsuarioRecurso.findByRecursos", query = "SELECT u FROM UsuarioRecurso u WHERE u.usuarioRecursoPK.recursos = :recursos"),
    @NamedQuery(name = "UsuarioRecurso.findByOperacao", query = "SELECT u FROM UsuarioRecurso u WHERE u.operacao = :operacao")})
public class UsuarioRecurso implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @EmbeddedId
    private UsuarioRecursoPK usuarioRecursoPK;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer operacao;

    /**
     *
     */
    public UsuarioRecurso() {
    }

    /**
     *
     * @param usuarioRecursoPK
     */
    public UsuarioRecurso(UsuarioRecursoPK usuarioRecursoPK) {
        this.usuarioRecursoPK = usuarioRecursoPK;
    }

    /**
     *
     * @param usuarioRecursoPK
     * @param operacao
     */
    public UsuarioRecurso(UsuarioRecursoPK usuarioRecursoPK, Integer operacao) {
        this.usuarioRecursoPK = usuarioRecursoPK;
        this.operacao = operacao;
    }

    /**
     *
     * @param idUsuario
     * @param recursos
     */
    public UsuarioRecurso(Long idUsuario, Integer recursos) {
        this.usuarioRecursoPK = new UsuarioRecursoPK(idUsuario, recursos);
    }

    /**
     *
     * @return
     */
    public UsuarioRecursoPK getUsuarioRecursosPK() {
        return usuarioRecursoPK;
    }

    /**
     *
     * @param usuarioRecursoPK
     */
    public void setUsuarioRecursosPK(UsuarioRecursoPK usuarioRecursoPK) {
        this.usuarioRecursoPK = usuarioRecursoPK;
    }

    /**
     *
     * @return
     */
    public Integer getOperacao() {
        return operacao;
    }

    /**
     *
     * @param operacao
     */
    public void setOperacao(Integer operacao) {
        this.operacao = operacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioRecursoPK != null ? usuarioRecursoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRecurso)) {
            return false;
        }
        UsuarioRecurso other = (UsuarioRecurso) object;
        if ((this.usuarioRecursoPK == null && other.usuarioRecursoPK != null) || (this.usuarioRecursoPK != null && !this.usuarioRecursoPK.equals(other.usuarioRecursoPK))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "UsuarioRecurso [usuarioRecursoPK=" + usuarioRecursoPK + "]";
	}



}
