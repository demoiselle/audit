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

import org.apache.commons.collections.ListUtils;
import org.primefaces.extensions.component.masterdetail.SelectLevelEvent;
import org.primefaces.extensions.component.timeline.TimelineUpdater;
import org.primefaces.extensions.event.timeline.TimelineLazyLoadEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;

import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.LocalTrail;
import br.gov.frameworkdemoiselle.component.audit.dashboard.domain.User;

/**
 *
 * @author SERPRO
 *
 */
public class UsersDashboardMB extends DashboardMB {

    private static final long serialVersionUID = 1L;
    private final int HOUR = 23;
    private final int MINUTE = 59;
    private final int SECOND = 59;

    private List<User> usersList;

    private User user;

    @PostConstruct
    public void initialize() {
        usersList = new ArrayList<User>();

        for (String user : trailBC.findByNamedQueryDistinct("LocalTrail.findDistinctUserName")) {
            usersList.add(new User(user));
        }

    }

    @Override
    public Integer navigationHadler(SelectLevelEvent e) {

        Integer nextLevel = e.getNewLevel();

        if (nextLevel == 1) {
            initialize();
        } else {
            search();
            fillUsers();
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
        dateFinal.set(Calendar.HOUR_OF_DAY, HOUR);
        dateFinal.set(Calendar.MINUTE, MINUTE);
        dateFinal.set(Calendar.SECOND, SECOND);

        trails = trailBC.findByNamedQueryWithBetween("LocalTrail.findByUserName", "userName", user.getUserName(), dateBegin.getTime(), dateFinal.getTime());
    }

    @SuppressWarnings("unchecked")
    public void onLazyLoad(TimelineLazyLoadEvent e) {

        Date dateBegin = e.getStartDateFirst();
        Date dateFinal = e.getEndDateFirst();

        TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":formTabs:tabs:timelineUser");

        trails = ListUtils.subtract(trailBC.findByNamedQueryWithBetween("LocalTrail.findByUserName", "userName", user.getUserName(), dateBegin, dateFinal), trails);

        for (LocalTrail trail : trails) {
            this.users.add(new TimelineEvent(trail, trail.getWhen(), false), timelineUpdater);
        }

    }

    public void setUser(Object object) {
        this.user = (User) object;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public User getUser() {
        return user;
    }

}
