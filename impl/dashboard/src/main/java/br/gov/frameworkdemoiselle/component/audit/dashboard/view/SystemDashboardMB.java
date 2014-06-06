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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;
import org.primefaces.extensions.component.timeline.TimelineUpdater;
import org.primefaces.extensions.event.timeline.TimelineLazyLoadEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;

import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.LocalTrail;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.System;

/**
 *
 * @author SERPRO
 *
 */
public class SystemDashboardMB extends DashboardMB {

    private static final long serialVersionUID = -3775274496607569244L;

    private List<System> systemList;

    private System system;

    @PostConstruct
    public void initialize() {
        systemList = new ArrayList<System>();
        for (String system : trailBC.findByNamedQueryDistinct("LocalTrail.findDistinctSystemName")) {
            systemList.add(new System(system));
        }
    }

    @Override
    public Integer navigationHadler(SelectLevelEvent e) {

        Integer nextLevel = e.getNewLevel();

        if (nextLevel == 1) {
            initialize();
        } else {
            search();
            fillSystems();
        }

        return nextLevel;
    }

    private void search() {

        Calendar dateBegin = Calendar.getInstance();
        dateBegin.set(Calendar.DAY_OF_MONTH, dateBegin.get(Calendar.DAY_OF_MONTH) - 5);
        dateBegin.set(Calendar.HOUR_OF_DAY, 0);
        dateBegin.set(Calendar.MINUTE, 0);
        dateBegin.set(Calendar.SECOND, 0);

        Calendar dateFinal = Calendar.getInstance();
        dateFinal.set(Calendar.HOUR_OF_DAY, 23);
        dateFinal.set(Calendar.MINUTE, 59);
        dateFinal.set(Calendar.SECOND, 59);

        trails = trailBC.findByNamedQueryWithBetween("LocalTrail.findBySystemName", "systemName", system.getSystemName(), dateBegin.getTime(), dateFinal.getTime());
    }

    public void onLazyLoad(TimelineLazyLoadEvent e) {

        Date dateBegin = e.getStartDateFirst();
        Date dateFinal = e.getEndDateFirst();

        TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":formTabs:tabs:timelineSystem");

        trails = trailBC.findByNamedQueryWithBetween("LocalTrail.findBySystemName", "systemName", system.getSystemName(), dateBegin, dateFinal);

        for (LocalTrail trail : trails) {
            this.systems.add(new TimelineEvent(trail, trail.getWhen(), false), timelineUpdater);
        }

    }

    public void setSystem(Object object) {
        this.system = (System) object;
    }

    public List<System> getSystemList() {
        return systemList;
    }

    public System getSystem() {
        return system;
    }

}
