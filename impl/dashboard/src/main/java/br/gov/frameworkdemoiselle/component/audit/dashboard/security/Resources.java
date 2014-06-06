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
public class Resources implements IResources {

    /**
     *
     * @param id
     * @return
     */
    public static String getResources(int id) {

        return "";
    }

    /**
     *
     * @param resource
     * @return
     */
    public static Integer getResource(String resource) {
        Integer number = Integer.valueOf(0);

        if (resource.equals(ATRIBUTO)) {
            number = 1;
        }
        if (resource.equals(DESCRITOR)) {
            number = 2;
        }
        if (resource.equals(EXCECAO)) {
            number = 3;
        }
        if (resource.equals(ORGAO)) {
            number = 4;
        }
        if (resource.equals(PAPEL)) {
            number = 5;
        }
        if (resource.equals(PESQUISAR)) {
            number = 6;
        }
        if (resource.equals(RECURSO)) {
            number = 7;
        }
        if (resource.equals(SECURITY)) {
            number = 8;
        }
        if (resource.equals(SERVICO)) {
            number = 9;
        }
        if (resource.equals(SISTEMA)) {
            number = 10;
        }
        if (resource.equals(MARCACAO)) {
            number = 12;
        }
        if (resource.equals(USUARIO)) {
            number = 13;
        }
        if (resource.equals(INVALID)) {
            number = 99;
        }

        return number;
    }

    /**
     *
     * @return
     */
    public static List<String> getResourcesList() {
        List<String> lista = new ArrayList<String>();

        lista.add(ATRIBUTO);
        lista.add(DESCRITOR);
        lista.add(EXCECAO);
        lista.add(ORGAO);
        lista.add(PAPEL);
        lista.add(PESQUISAR);
        lista.add(RECURSO);
        lista.add(SECURITY);
        lista.add(SERVICO);
        lista.add(SISTEMA);
        lista.add(MARCACAO);
        lista.add(USUARIO);

        lista.add(INVALID);

        return lista;
    }

}
