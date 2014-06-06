package br.gov.frameworkdemoiselle.component.audit.processors.rest;

import br.gov.frameworkdemoiselle.annotation.Name;
import br.gov.frameworkdemoiselle.configuration.Configuration;

@Configuration(prefix = "frameworkdemoiselle.audit.processor.mongo")
public class MONGOConfig {
	
    @Name("server.url")
    private String serverUrl;

    @Name("database.name")
    private String dataBaseName;
    
    @Name("database.user")
    private String databaseUser;
    
    @Name("database.password")
    private String databasePass;
    
    @Name("collection.name")
    private String collectionName;

    public String getDataBaseName() {
            return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
            this.dataBaseName = dataBaseName;
    }

    public String getDatabaseUser() {
            return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
            this.databaseUser = databaseUser;
    }

    public String getDatabasePass() {
            return databasePass;
    }

    public void setDatabasePass(String databasePass) {
            this.databasePass = databasePass;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getServerUrl() {
            return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
            this.serverUrl = serverUrl;
    }
	
}
