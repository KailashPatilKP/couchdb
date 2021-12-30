package com.couchdb.configuration;

import java.net.MalformedURLException;
import java.util.UUID;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.DbPath;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.DesignDocument;

public class CouchDBDemo {

	public static CouchDbConnector createAndConnectDatabase;
	public static CouchDbInstance couchDbInstance;

	public static void main(String[] args) throws MalformedURLException {
//		HttpClient httpClient = new StdHttpClient.Builder().url("http://localhost:5984").password("admin")
//				.username("admin").build();
//		// {"couchdb":"Welcome","version":"3.2.0","git_sha":"efb409bba","uuid":"00ff89b4d520d85699a5e8e39d3df88a","features":["access-ready","partitioned","pluggable-storage-engines","reshard","scheduler"],"vendor":{"name":"The
//		// Apache Software Foundation"}}
//		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
//		CouchDbConnector db = new StdCouchDbConnector("example", dbInstance);
//		db.createDatabaseIfNotExists();
//		DesignDocument dd = new DesignDocument("xyz");
//		db.create(dd);
		System.out.println("Connecting CounchDB...");
		createAndConnectDatabase = CouchDBDemo.couchDbConnector("product");
		System.out.println("Connected: couchDbConnector" + createAndConnectDatabase);

		System.out.println("Creating document");
		//CouchDBDemo.createDocument(couchDbConnector, "product");
		
		Product product = new Product();
		product.setName("Ramanujadasu21111");
		product.setId(UUID.randomUUID()+"");
		product.setRevision(null);

		createAndConnectDatabase.create(product);

		product = createAndConnectDatabase.get(Product.class,product.getId());
		System.out.println("product:" + product.getName());
		
		System.out.println("Created given document successfully");

		// CouchDbConnector connector = dbInstance.createConnector("person", true);

		
//		
//		CommitCRUD commitCRUD = new CommitCRUD(db); 
//        
//        Commit commit = new Commit(); 
//        commit.setId("myid"); 
//        commit.setMessage("coucou"); 
//        commit.setReplica(42); 
//        commitCRUD.add(commit); 
//         
//        Commit result = commitCRUD.get("myid"); 
//        assertEquals("myid", result.getId()); 
//        assertEquals("coucou", result.getMessage()); 
//        assertEquals(42, result.getReplica()); 
//         
//        Commit result2 = commitCRUD.getAll().get(0); 
//        assertEquals("myid", result2.getId()); 
//        assertEquals("coucou", result2.getMessage()); 
//        assertEquals(42, result2.getReplica()); 

	}

	public static CouchDbConnector couchDbConnector(String dbName) throws MalformedURLException {

		HttpClient httpClient = new StdHttpClient.Builder().url("http://localhost:5984").password("admin")
				.username("admin").build();
		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);

		// if the second parameter is true, the database will be created if it doesn't
		// exists
		// CouchDbConnector db = dbInstance.createConnector("couched-test", true);
		return dbInstance.createConnector(dbName, true);
	}

	public static CouchDbInstance couchDbInstance() throws MalformedURLException {

		HttpClient httpClient = new StdHttpClient.Builder().url("http://localhost:5984").password("admin")
				.username("admin").build();
		CouchDbInstance couchDbInstance = new StdCouchDbInstance(httpClient);
		return couchDbInstance;
	}

	public static CouchDbConnector createAndConnectDatabase(String databaseName) throws MalformedURLException {

		// if the second parameter is true, the database will be created if it doesn't
		// exists

		return couchDbInstance.createConnector(databaseName, true);
	}

	public static void createDocument(CouchDbConnector cb, String name) {

		DesignDocument dd = new DesignDocument(name);
		createAndConnectDatabase.create(dd);
	}

	public void storeAnrRetrieve() throws MalformedURLException {
		HttpClient httpClient = new StdHttpClient.Builder().url("http://localhost:5984").build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
		if (dbInstance.checkIfDbExists(new DbPath("test_commit"))) {
			dbInstance.deleteDatabase("test_commit");
		}
		CouchDbConnector db = new StdCouchDbConnector("test", dbInstance);
		db.createDatabaseIfNotExists();
	}

}
