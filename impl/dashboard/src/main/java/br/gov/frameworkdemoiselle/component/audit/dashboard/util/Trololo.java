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
package br.gov.frameworkdemoiselle.component.audit.dashboard.util;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author SERPRO
 * 
 */

public class Trololo {

    private Random rd = new Random();

    private String[] sistema = {"SISCOP", "SVPC", "SIAFI", "SGP", "SISAUD"};
    private String[] usuario = {"Maria", "João", "José", "Francisco", "Roberto"};
    private String[] perfil = {"Admin", "Guest", "Gerente", "Básico", "Completo"};
    private String[] oque = {"Incluiu", "Alterou", "Consultou", "Excluiu", "Entrou"};
    private String[] idname = {"id", "codigo", "tabctidcta", "tb_ct_cod", "idobjecto"};

    private String[] clazz = {"br.gov.serpro.siscop.domain.Usuario", "br.gov.serpro.siafi.domain.Trilha", "br.gov.serpro.siscop.Ponto", "br.gov.serpro.Geral"};
    private String[] campo1 = {"nome", "usuario"};
    private String[] campo2 = {"sobrenome", "filho"};
    private String[] valores = {"Antonio", "Roberto", "Silva", "Camargo", "Oliveira", "José", "Santos", "Roméro"};

    public String getSistema() {
        return sistema[random(sistema)];
    }

    public String getUsuario() {
        return usuario[random(usuario)];
    }

    public String getPerfil() {
        return perfil[random(perfil)];
    }

    public String getOque() {
        return oque[random(oque)];
    }

    public String getOnde() {
        return "" + rd.nextInt(250) + "." + "" + rd.nextInt(250) + "." + "" + rd.nextInt(250) + "." + "" + rd.nextInt(250);
    }

    public String getIdName() {
        return idname[random(idname)];
    }

    public Date getQuando() {
        Date data = new Date();
        data.setMonth(data.getMonth() - (rd.nextInt(11)));
        data.setDate(data.getDate() - (rd.nextInt(28)));
        data.setHours(data.getHours() + (rd.nextInt(9) + 8));
        data.setMinutes(data.getMinutes() + rd.nextInt(60));
        data.setSeconds(data.getSeconds() + rd.nextInt(60));
        return data;
    }

    public String getObjetoSerial() {

        StringBuilder sb = new StringBuilder();
        sb.append("{\"class\": \"");
        sb.append(clazz[random(clazz)]);
        sb.append("\", ");
        
        sb.append("\"");
        sb.append("id");
        sb.append("\":");
        sb.append(rd.nextInt(50));
        sb.append(", ");
        
        sb.append("\"");
        sb.append(campo1[random(campo1)]);
        sb.append("\": \"");
        sb.append(valores[random(valores)]);

        if (rd.nextInt(10) >= 5) {
            sb.append("\", ");
            sb.append("\"");
            sb.append(campo2[random(campo2)]);
            sb.append("\": \"");
            sb.append(valores[random(valores)]);
        }

        sb.append("\"}");

        return sb.toString();
    }

    private int random(String[] array) {

        int retorno = rd.nextInt(array.length);
        if (retorno == array.length) {
            retorno--;
        }

        return retorno;
    }

}
