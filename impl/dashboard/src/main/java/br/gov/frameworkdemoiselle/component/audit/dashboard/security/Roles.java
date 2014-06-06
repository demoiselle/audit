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
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import br.gov.frameworkdemoiselle.component.audit.dashboard.constant.RolesConfiguration;
import java.util.Map;


/**
 * 
 * @author SERPRO
 * 
 */
public class Roles implements IRoles {

    @Inject
    private RolesConfiguration rc;

    /**
     *
     * @param role
     * @return
     */
    public static List<String> getRole(Short role) {
        List<String> list = new ArrayList<String>();
        switch (role) {
            case 1:
                list.add(USER);
                break;
            case 2:
                list.add(MANAGER);
                break;
            case 3:
                list.add(USER);
                list.add(MANAGER);
                break;
            case 4:
                list.add(ADMINISTRATOR);
                break;
            case 5:
                list.add(USER);
                list.add(ADMINISTRATOR);
                break;
            case 6:
                list.add(MANAGER);
                list.add(ADMINISTRATOR);
                break;
            case 7:
                list.add(USER);
                list.add(MANAGER);
                list.add(ADMINISTRATOR);
                break;
            default:
                list.add(INVALID);
                break;
        }
        return list;
    }

    /**
     *
     * @return
     */
    public static List<String> getRolesList() {
        List<String> list = new ArrayList<String>();
        list.add(USER);
        list.add(MANAGER);
        list.add(ADMINISTRATOR);
        list.add(INVALID);
        return list;
    }

    public static Map<Short, String> getRolesListAsMap(){

    	HashMap<Short, String> papeis = new HashMap<Short, String>();

    	papeis.put(getRole(USER), USER);
    	papeis.put(getRole(MANAGER), MANAGER);
    	papeis.put(getRole(ADMINISTRATOR), ADMINISTRATOR);
    	papeis.put(getRole(INVALID), INVALID);

    	return papeis;
    }

    /**
     *
     * @param listRole
     * @return
     */
    public static Long getRole(List<String> listRole) {
        Long number = 0L;

        if (listRole.contains(USER)) {
            number += 1L;
        }
        if (listRole.contains(MANAGER)) {
            number += 2L;
        }
        if (listRole.contains(ADMINISTRATOR)) {
            number += 4L;
        }
        if (listRole.contains(INVALID)) {
            number += 8L;
        }

        return number;
    }

    /**
     *
     * @param role
     * @return
     */
    public static Short getRole(String role) {

        if (role.equals(USER)) {
            return 1;
        }
        if (role.equals(MANAGER)) {
            return 2;
        }
        if (role.equals(ADMINISTRATOR)) {
            return 4;
        }

        return 0;
    }
}
