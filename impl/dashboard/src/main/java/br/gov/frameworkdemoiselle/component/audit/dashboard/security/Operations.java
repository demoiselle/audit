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

/**
 * 
 * @author SERPRO
 * 
 */
public class Operations implements IOperations{

    /**
     *
     * @param sum
     * @return
     */
    public static List<String> listOperations(int sum) {
        List<String> list = new ArrayList<String>();
        list.add("LIST");
        switch (sum) {
            case 1:
                list.add(DELETE);
                break;
            case 2:
                list.add(INSERT);
                break;
            case 3:
                list.add(INSERT);
                list.add(UPDATE);
                break;
            case 4:
                list.add(DELETE);
                break;
            case 5:
                list.add(UPDATE);
                list.add(DELETE);
                break;
            case 6:
                list.add(INSERT);
                list.add(DELETE);
                break;
            case 7:
                list.add(UPDATE);
                list.add(INSERT);
                list.add(DELETE);
                break;
            default:
                list.add(INVALID);
                break;
        }
        return list;

    }

    /**
     *
     * @param listOp
     * @return
     */
    public static int getOperation(List<String> listOp) {
        int number = 0;
        if (listOp.contains(UPDATE)) {
            number += 1;
        }
        if (listOp.contains(INSERT)) {
            number += 2;
        }
        if (listOp.contains(DELETE)) {
            number += 4;
        }
        return number;
    }

    Operations() {

    }
}
