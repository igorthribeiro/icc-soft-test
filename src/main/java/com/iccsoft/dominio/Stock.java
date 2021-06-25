package com.iccsoft.dominio;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "QuoteList", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "quote")
	private List<Double> quotes;

	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Double> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Double> quotes) {
		this.quotes = quotes;
	}

	@Override
	public String toString() {
		return "Stock {name: " + name + ", quotes: " + quotes + "}";
	}

}
