package com.couchdb.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
	@JsonProperty("_id")
	private String id;
	@JsonProperty("_rev")
	private String revision;
	private String name;

	public String getId() {
		return id;
	}

	public String getRevision() {
		return revision;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public void setName(String name) {
		this.name = name;
	}
}