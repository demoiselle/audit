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
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import java.util.Objects;
import javax.persistence.Temporal;

/**
 *
 * @author SERPRO
 *
 */
@Entity(name = "LocalTrail")
@Table(name = "Trail")
@XmlRootElement(name = "Trail")
@NamedQueries({
    @NamedQuery(name = "LocalTrail.findAll", query = "SELECT d FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findByIdaudit", query = "SELECT d FROM LocalTrail d WHERE d.idaudit = :idaudit"),
    @NamedQuery(name = "LocalTrail.findBySystemName", query = "SELECT d FROM LocalTrail d WHERE d.systemName = :systemName AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByUserName", query = "SELECT d FROM LocalTrail d WHERE d.userName = :userName AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByWhat", query = "SELECT d FROM LocalTrail d WHERE d.what = :what AND d.when BETWEEN :whenBegin AND :whenFinal"),    
    @NamedQuery(name = "LocalTrail.findByWhere", query = "SELECT d FROM LocalTrail d WHERE d.where = :where AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findByWhen", query = "SELECT d FROM LocalTrail d WHERE d.when = :when AND d.when BETWEEN :whenBegin AND :whenFinal"),
    @NamedQuery(name = "LocalTrail.findDistinctSystemName", query = "SELECT DISTINCT d.systemName FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findDistinctUserName", query = "SELECT DISTINCT d.userName FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findDistinctWhat", query = "SELECT DISTINCT d.what FROM LocalTrail d"),
    @NamedQuery(name = "LocalTrail.findDistinctClassName", query = "SELECT DISTINCT d.className FROM LocalTrail d WHERE d.systemName = :systemName"),
    @NamedQuery(name = "LocalTrail.findIdNameBySystemAndObject", query = "SELECT DISTINCT d.idName FROM LocalTrail d WHERE d.systemName = :systemName AND d.className = :className"),
    @NamedQuery(name = "LocalTrail.findByNamedQuerySystemAndObjectAndIdName", query = "SELECT d FROM LocalTrail d WHERE d.systemName = :systemName AND d.className = :className AND d.objSerial LIKE :value ORDER BY d.when, d.idaudit")
})
public class LocalTrail implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idaudit;
    private String systemName;
    private String userName;
    private String idName;
    private String profile;
    @Column(name = "_WHAT")
    private String what;
    @Column(name = "_WHERE")
    private String where;
    @Column(name = "_WHEN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date when;
    private String className;
    private String objSerial;
    private String layerName;
    private String processorName;

    /**
     *
     */
    public LocalTrail() {
    }

    public LocalTrail(Long idaudit) {
        this.idaudit = idaudit;
    }

    public LocalTrail(Long idaudit, String systemName, String userName,
            String idName, String profile, String what, String where,
            Date when, String className, String objSerial,
            String layerName, String processorName) {
        super();
        this.idaudit = idaudit;
        this.systemName = systemName;
        this.userName = userName;
        this.idName = idName;
        this.profile = profile;
        this.what = what;
        this.where = where;
        this.when = when;        
        this.className = className;
        this.objSerial = objSerial;
        this.layerName = layerName;
        this.processorName = processorName;
    }

    public LocalTrail(Long idaudit, Trail trail) {

        this.idaudit = idaudit;
        this.systemName = trail.getSystemName();
        this.userName = trail.getUserName();
        this.idName = trail.getIdName();
        this.profile = trail.getProfile();
        this.what = trail.getWhat();
        this.where = trail.getWhere();
        this.when = trail.getWhen();
        this.className = trail.getClassName();
        this.objSerial = trail.getObjSerial();
        this.layerName = trail.getLayerName();
        this.processorName = trail.getProcessorName();

    }

    public LocalTrail(Trail trail) {      
        this.systemName = trail.getSystemName();
        this.userName = trail.getUserName();
        this.idName = trail.getIdName();
        this.profile = trail.getProfile();
        this.what = trail.getWhat();
        this.where = trail.getWhere();
        this.setWhen(trail.getWhen());
        this.className = trail.getClassName();
        this.objSerial = trail.getObjSerial();
        this.layerName = trail.getLayerName();
        this.processorName = trail.getProcessorName();
    }

    public Long getIdaudit() {
        return idaudit;
    }

    public void setIdaudit(Long idaudit) {
        this.idaudit = idaudit;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public Date getWhen() {
        return (Date) when.clone();
    }

    public void setWhen(Date when) {
        this.when = new Date(when.getTime());
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getObjSerial() {
        return objSerial;
    }

    public void setObjSerial(String objSerial) {
        this.objSerial = objSerial;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.idaudit);
        hash = 41 * hash + Objects.hashCode(this.systemName);
        hash = 41 * hash + Objects.hashCode(this.idName);
        hash = 41 * hash + Objects.hashCode(this.when);
        hash = 41 * hash + Objects.hashCode(this.objSerial);
        hash = 41 * hash + Objects.hashCode(this.processorName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LocalTrail other = (LocalTrail) obj;
        if (!Objects.equals(this.idaudit, other.idaudit)) {
            return false;
        }
        if (!Objects.equals(this.systemName, other.systemName)) {
            return false;
        }
        if (!Objects.equals(this.idName, other.idName)) {
            return false;
        }
        if (!Objects.equals(this.when, other.when)) {
            return false;
        }
        if (!Objects.equals(this.objSerial, other.objSerial)) {
            return false;
        }
        if (!Objects.equals(this.processorName, other.processorName)) {
            return false;
        }
        return true;
    }

    
}
