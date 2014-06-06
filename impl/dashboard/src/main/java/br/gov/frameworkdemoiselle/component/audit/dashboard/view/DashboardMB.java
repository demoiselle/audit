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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

import br.gov.frameworkdemoiselle.component.audit.dashboard.business.TrilhaBC;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.SerialObject;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.LocalTrail;
import br.gov.frameworkdemoiselle.component.audit.implementation.util.Util;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractPageBean;

/**
 *
 * @author SERPRO
 *
 */

@ViewController
public class DashboardMB extends AbstractPageBean implements DashboardNavigationHandler {

	private static final long serialVersionUID = 4565271684849353654L;

	@Inject
	protected TrilhaBC trailBC;

	protected int currentLevel = 1;

	protected List<LocalTrail> trails;

	protected TimelineEvent event;
	protected SerialObject serialObject;

	protected TimelineModel systems;
	protected TimelineModel features;
	protected TimelineModel users;

	protected void fillSystems() {
		systems = new TimelineModel();

		for(LocalTrail trail : trails){
			systems.add(new TimelineEvent(trail, trail.getWhen(), false));
		}
	}

	protected void fillFeatures() {
		features = new TimelineModel();

		for(LocalTrail trail : trails){
			features.add(new TimelineEvent(trail, trail.getWhen(), false));
		}
	}

	protected void fillUsers() {
		users = new TimelineModel();

		for(LocalTrail trail : trails){
			users.add(new TimelineEvent(trail, trail.getWhen(), false));
		}

	}

	public void onSelect(TimelineSelectEvent e){
		this.event = e.getTimelineEvent();
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public List<LocalTrail> getTrilhas() {
		return trails;
	}

	public void setTrails(List<LocalTrail> trails) {
		this.trails = trails;
	}

	public TimelineEvent getEvent() {

		if(event != null && event.getData() != null){

			LocalTrail trail = (LocalTrail) event.getData();
			Map<String, String> map = Util.jsonToMap(trail.getObjSerial());

			serialObject = new SerialObject(trail.getClassName(), map);
		}

		return event;
	}

	public List<String> getKeys(){
		List<String> keys = new ArrayList<String>();

		if(serialObject != null && serialObject.getMap() != null){
			for(String key : serialObject.getMap().keySet()){
				if(!key.equals("class")){
					keys.add(key);
				}
			}
		}

		return keys;
	}

	public void setEvent(TimelineEvent event) {
		this.event = event;
	}

	public TrilhaBC getTrailBC() {
		return trailBC;
	}

	@Override
	public Integer navigationHadler(SelectLevelEvent e) {
		return null;
	}

	public TimelineModel getSystems() {
		return systems;
	}

	public TimelineModel getFeatures() {
		return features;
	}

	public TimelineModel getUsers() {
		return users;
	}

	public SerialObject getSerialObject() {
		return serialObject;
	}

}
