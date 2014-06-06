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
package br.gov.frameworkdemoiselle.component.audit.internal.scheduler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.util.AnnotationLiteral;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.gov.frameworkdemoiselle.component.audit.AuditException;
import br.gov.frameworkdemoiselle.component.audit.domain.Trail;
import br.gov.frameworkdemoiselle.component.audit.implementation.AuditConfig;
import br.gov.frameworkdemoiselle.component.audit.implementation.processor.AbstractProcessor;
import br.gov.frameworkdemoiselle.component.audit.implementation.qualifier.AuditProcessorFail;
import br.gov.frameworkdemoiselle.component.audit.implementation.util.Util;
import br.gov.frameworkdemoiselle.util.Beans;

/**
 *
 * @author SERPRO
 *
 */
public class AuditJob implements Job {

    private final AuditConfig config = Beans.getReference(AuditConfig.class);
    private final BeanManager beanManager = Beans.getBeanManager();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        String folderName = config.getFolderFailObjects();

        if (folderName == null || folderName.isEmpty() || "".equals(folderName)) {
            folderName = Util.getFolderPathDefault();
        }

        File folder = new File(folderName);
        File files[] = folder.listFiles();

        if (files != null && files.length > 0) {
            Logger.getLogger(AuditJob.class.getName()).log(Level.INFO, "Number of files: " + files.length);

            for (File file : files) {
                ObjectInput input = null;
                InputStream is = null;
                Trail trail = null;

                Boolean isDeleted = Boolean.FALSE;

                try {
                    is = new FileInputStream(file);
                    InputStream buffer = new BufferedInputStream(is);

                    input = new ObjectInputStream(buffer);
                    trail = (Trail) input.readObject();

                    String processorClass = trail.getProcessorName();

                    if (processorClass != null && !processorClass.isEmpty()) {

                        AbstractProcessor processor = (AbstractProcessor) Class.forName(processorClass).newInstance();
                        processor.execute(trail);

                        isDeleted = file.delete();
                    }

                } catch (IOException e) {
                    notifyFail(isDeleted, trail);
                    throw new AuditException("Fail reprocessor file '" + file + "' , message error :" + e.getMessage(), e);
                } catch (ClassNotFoundException e) {
                    notifyFail(isDeleted, trail);
                    throw new AuditException("Fail reprocessor file '" + file + "' , message error :" + e.getMessage(), e);
                } catch (InstantiationException e) {
                    notifyFail(isDeleted, trail);
                    throw new AuditException("Fail reprocessor file '" + file + "' , message error :" + e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    notifyFail(isDeleted, trail);
                    throw new AuditException("Fail reprocessor file '" + file + "' , message error :" + e.getMessage(), e);
                } finally {
                     if(input != null){
                        try{
                            input.close();
                        }
                        catch (IOException e) {
                            notifyFail(isDeleted, trail);
                            Logger.getLogger(AuditJob.class.getName()).log(Level.SEVERE, "Fail to close inputstream: " + e.getMessage());
                        }
                    }
                     
                    if (is != null) {
                        try {
                            is.close();
                        } catch (IOException e) {
                            notifyFail(isDeleted, trail);
                            Logger.getLogger(AuditJob.class.getName()).log(Level.SEVERE, "Fail to close inputstream: " + e.getMessage());
                        }
                    }
                    
                   
                }
            }

        }

    }

    @SuppressWarnings("serial")
    private void notifyFail(Boolean isDeleted, Trail trail) {
        if (isDeleted && trail != null) {
            beanManager.fireEvent(trail, new AnnotationLiteral<AuditProcessorFail>() {
            });
        }

    }

}
