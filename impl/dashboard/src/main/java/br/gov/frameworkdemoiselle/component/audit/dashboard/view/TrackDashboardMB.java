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
package br.gov.frameworkdemoiselle.component.audit.dashboard.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import br.gov.frameworkdemoiselle.component.audit.dashboard.business.TrilhaBC;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.SerialObject;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.LocalTrail;
import br.gov.frameworkdemoiselle.component.audit.implementation.util.Util;
import br.gov.frameworkdemoiselle.stereotype.ViewController;

/**
 *
 * @author SERPRO
 *
 */
@ViewController
public class TrackDashboardMB implements Serializable {

    private static final long serialVersionUID = -7024316116402884400L;

    @Inject
    protected TrilhaBC trailBC;

    private List<String> systems;
    private List<String> objects;
    private String objectIdName;
    private String objectIdValue;

    private String system;
    private String object;

    private List<String> fields;

    private List<SerialObject> tracks;
    private List<String> objectFields;

    @PostConstruct
    public void initialize() {
        systems = trailBC.findByNamedQueryDistinct("LocalTrail.findDistinctSystemName");
        objects = new ArrayList<String>();
        objectIdName = "";
        objectIdValue = "";
    }

    public void onChangeSystem(ValueChangeEvent event) {
        system = (String) event.getNewValue();
        objects = trailBC.findByNamedQueryDistinct("LocalTrail.findDistinctClassName", "systemName", system);
        objectIdName = "";
        objectIdValue = "";
    }

    public void onChangeObject(ValueChangeEvent event) {
        object = (String) event.getNewValue();
        objectIdName = trailBC.findByNamedQueryIdName("LocalTrail.findIdNameBySystemAndObject", system, object);
        objectIdValue = "";
    }

    public void search(ActionEvent event) {

        if (objectIdValue != null && !"".equals(objectIdValue)) {

            List<LocalTrail> trails = trailBC.findByNamedQuerySystemAndObjectAndIdName("LocalTrail.findByNamedQuerySystemAndObjectAndIdName", system, object, objectIdName, objectIdValue);

            tracks = new ArrayList<SerialObject>();

            for (LocalTrail trilha : trails) {
                if (fields == null || fields.size() == 0) {
                    fillFields(trilha);
                }

                SerialObject serialObject = new SerialObject();
                serialObject.setMap(Util.jsonToMap(trilha.getObjSerial()));
                serialObject.setTrilha(trilha);

                tracks.add(serialObject);

            }
        }

    }

    private void fillFields(LocalTrail trilha) {
        fields = new ArrayList<String>();

        Map<String, String> map = Util.jsonToMap(trilha.getObjSerial());
        fields.addAll(map.keySet());

        objectFields = new ArrayList<String>();

        for (String key : map.keySet()) {
            if (!key.equals("class")) {
                objectFields.add(key);
            }
        }

    }

    public List<String> getObjectFields() {
        return objectFields;
    }

    public List<String> getSystems() {
        return systems;
    }

    public void setSystems(List<String> systems) {
        this.systems = systems;
    }

    public List<String> getObjects() {
        return objects;
    }

    public void setObjects(List<String> objects) {
        this.objects = objects;
    }

    public String getObjectIdName() {
        return objectIdName;
    }

    public void setObjectIdName(String objectIdName) {
        this.objectIdName = objectIdName;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getObjectIdValue() {
        return objectIdValue;
    }

    public void setObjectIdValue(String objectIdValue) {
        this.objectIdValue = objectIdValue;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public List<SerialObject> getTracks() {
        return tracks;
    }

    public void setTracks(List<SerialObject> tracks) {
        this.tracks = tracks;
    }

}
