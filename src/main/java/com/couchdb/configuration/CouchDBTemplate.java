package com.couchdb.configuration;

import java.net.MalformedURLException;
import java.util.UUID;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;

public class CouchDBTemplate {

	public static CouchDbInstance couchDbInstance = CouchDBInstance.couchDbInstance();

	public static CouchDbConnector createAndConnectDatabase(String databaseName) throws MalformedURLException {

		// if the second parameter is true, the database will be created if it doesn't
		// exists
		return couchDbInstance.createConnector(databaseName, true);
	}

	public static void createDocument(String dbName, Product obj) {

		couchDbInstance.createConnector(dbName, false).create(obj);
	}

	public static Object getDocumentById(String dbName, Object type, String id) {

		return couchDbInstance.createConnector(dbName, false).get(Object.class, id);
	}

	public static Product getDocumentById(String dbName, Product type, String id) {

		return couchDbInstance.createConnector(dbName, false).get(Product.class, id);
	}

	public static void main(String[] args) throws MalformedURLException {
		System.out.println("*******************");
		System.out.println("Connecting CounchDB...");
		CouchDbInstance couchDbInstance = CouchDBInstance.couchDbInstance();
		System.out.println("Connected: couchDbInstance" + couchDbInstance);

		System.out.println("*******************");

		String database = "product";
		System.out.println("Creating database(Collection)");
		CouchDBTemplate.createAndConnectDatabase(database);
		System.out.println("Created given database successfully");

		System.out.println("*******************");

		System.out.println("Creating document for the " + database);
		Product product = new Product();
		product.setName("Ramanujadasu");
		product.setId(UUID.randomUUID() + "");
		CouchDBTemplate.createDocument(database, product);

		System.out.println("Created document " + product.getId() + " in the " + database + " database");
		System.out.println("*******************");
		System.out.println("Get document from the " + database);

		product = CouchDBTemplate.getDocumentById(database, product, product.getId());

		System.out.println("product id:" + product.getId());
		System.out.println("product name:" + product.getName());
		System.out.println("product rev:" + product.getRevision());

		System.out.println("Retrived " + database + " document successfully");
		System.out.println("*******************");
	}
}
