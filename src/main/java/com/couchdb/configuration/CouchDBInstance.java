package com.couchdb.configuration;

import java.net.MalformedURLException;

import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

public class CouchDBInstance {

	public static CouchDbInstance couchDbInstance() {
		HttpClient httpClient;
		CouchDbInstance couchDbInstance = null;
		try {
			httpClient = new StdHttpClient.Builder().url("http://localhost:5984").password("admin").username("admin")
					.build();
			couchDbInstance = new StdCouchDbInstance(httpClient);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return couchDbInstance;
	}
	

}
