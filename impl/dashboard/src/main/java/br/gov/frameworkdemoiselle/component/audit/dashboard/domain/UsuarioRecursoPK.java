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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author SERPRO
 * 
 */

@Embeddable
public class UsuarioRecursoPK implements Serializable {

	private static final long serialVersionUID = -1849399048118149823L;

	@Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer recursos;

    /**
     *
     */
    public UsuarioRecursoPK() {
    }

    /**
     *
     * @param idUsuario
     * @param recursos
     */
    public UsuarioRecursoPK(Long idUsuario, Integer recursos) {
        this.idUsuario = idUsuario;
        this.recursos = recursos;
    }

    /**
     *
     * @return
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     *
     * @param idUsuario
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     *
     * @return
     */
    public int getRecursos() {
        return recursos;
    }

    /**
     *
     * @param recursos
     */
    public void setRecursos(Integer recursos) {
        this.recursos = recursos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Long) idUsuario;
        hash += (Integer) recursos;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioRecursoPK)) {
            return false;
        }
        UsuarioRecursoPK other = (UsuarioRecursoPK) object;
        if (!this.idUsuario.equals(other.idUsuario)) {
            return false;
        }
        if (!this.recursos.equals(other.recursos)) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "UsuarioRecursoPK [idUsuario=" + idUsuario + "]";
	}
   

}
