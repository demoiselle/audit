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
package br.gov.frameworkdemoiselle.component.audit.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SERPRO
 *
 */

public class Trail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private final int HASH_CODE_5 = 5;
    private final int HASH_CODE_37 = 37;

    /**
     *
     */
    private String systemName;
    private String userName;
    private String idName;
    private String profile;
    private String what;
    private String where;
    private Date when;
    private String className;
    private String objSerial;
    private String layerName;
    private String processorName;

    /**
     *
     */
    public Trail(){
    }

    /**
     *
     * @param systemName
     * @param userName
     * @param idName
     * @param profile
     * @param what
     * @param how
     * @param where
     * @param when
     * @param className
     * @param objSerial
     * @param layerName
     * @param processorName
     */
    public Trail(String systemName, String userName, String idName,
                 String profile, String what, String where, Date when,
                 String className, String objSerial, String layerName,
                 String processorName) {
        this.systemName = systemName;
        this.userName = userName;
        this.idName = idName;
        this.profile = profile;
        this.what = what;
        this.where = where;
        this.setWhen(when);
        this.className = className;
        this.objSerial = objSerial;
        this.layerName = layerName;
        this.processorName = processorName;
    }

    /**
     *
     * @return
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     *
     * @param systemName
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     *
     * @return
     */
    public String getWhat() {
        return what;
    }

    /**
     *
     * @param what
     */
    public void setWhat(String what) {
        this.what = what;
    }

    /**
     *
     * @return
     */
    public String getWhere() {
        return where;
    }

    /**
     *
     * @param where
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     *
     * @return
     */
    public Date getWhen() {
        return (Date) when.clone();
    }

    /**
     *
     * @param when
     */
    public void setWhen(Date when) {
        this.when = new Date(when.getTime());
    }

    /**
     *
     * @return
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @param className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     *
     * @return
     */
    public String getObjSerial() {
        return objSerial;
    }

    /**
     *
     * @param objSerial
     */
    public void setObjSerial(String objSerial) {
        this.objSerial = objSerial;
    }

    /**
     *
     * @return
     */
    public String getLayerName() {
        return layerName;
    }

    /**
     *
     * @param layerName
     */
    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    /**
     *
     * @return
     */
    public String getProcessorName() {
        return processorName;
    }

    /**
     *
     * @param processorName
     */
    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

    /**
     *
     * @return
     */
    public String getIdName() {
        return idName;
    }

    /**
     *
     * @param idName
     */
    public void setIdName(String idName) {
        this.idName = idName;
    }

    @Override
    public String toString() {
        return "Trail{" + "systemName=" + systemName + ", userName=" + userName + ", idName=" + idName + ", profile=" + profile + ", what=" + what + ", where=" + where + ", when=" + when + ", className=" + className + ", objSerial=" + objSerial + ", layerName=" + layerName + ", processorName=" + processorName + '}';
    }

    @Override
    public int hashCode() {
        int hash = HASH_CODE_5;
        hash = HASH_CODE_37 * hash + (this.userName != null ? this.userName.hashCode() : 0);
        hash = HASH_CODE_37 * hash + (this.idName != null ? this.idName.hashCode() : 0);
        hash = HASH_CODE_37 * hash + (this.className != null ? this.className.hashCode() : 0);
        hash = HASH_CODE_37 * hash + (this.objSerial != null ? this.objSerial.hashCode() : 0);
        hash = HASH_CODE_37 * hash + (this.layerName != null ? this.layerName.hashCode() : 0);
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
        final Trail other = (Trail) obj;
        if ((this.userName == null) ? (other.userName != null) : !this.userName.equals(other.userName)) {
            return false;
        }
        if ((this.idName == null) ? (other.idName != null) : !this.idName.equals(other.idName)) {
            return false;
        }
        if ((this.className == null) ? (other.className != null) : !this.className.equals(other.className)) {
            return false;
        }
        if ((this.objSerial == null) ? (other.objSerial != null) : !this.objSerial.equals(other.objSerial)) {
            return false;
        }
        if ((this.layerName == null) ? (other.layerName != null) : !this.layerName.equals(other.layerName)) {
            return false;
        }
        return true;
    }

   
}
