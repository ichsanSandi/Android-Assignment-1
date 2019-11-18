package com.example.program1.model;

public class Drink
{
	private String id;
	private String name;
	private int price;
	private String key;
	
	public Drink ()
		{	}
	
	public Drink (String name, String id, int price)
	{
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public void setId (String id)
		{ this.id = id; }
	
	public void setName (String name)
		{ this.name = name; }
	
	public void setPrice (int price)
		{ this.price = price; }
	
	public String getId ()
		{ return id; }
	
	public String getName ()
		{ return name; }
	
	public int getPrice ()
		{ return price; }
	
	public String getKey ()
		{ return key; }
	
	public void setKey (String key)
		{ this.key = key; }
}