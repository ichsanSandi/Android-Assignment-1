package com.example.program1.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelMakanan
{
	private String key;
	private String idMakanan;
	private String namaMakanan;
	private String hargaMakanan;
	private String fotoMakanan;

	public ModelMakanan ()
	{	}
	
	public ModelMakanan (String idMakanan, String namaMakanan, String hargaMakanan, String fotoMakanan)
	{
		this.idMakanan = idMakanan;
		this.namaMakanan = namaMakanan;
		this.hargaMakanan = hargaMakanan;
		this.fotoMakanan = fotoMakanan;
	}
			
	public String getIdMakanan ()
		{ return idMakanan; }
			
	public void setIdMakanan (String idMakanan)
		{ this.idMakanan = idMakanan; }
	
	public String getNamaMakanan ()
		{ return namaMakanan; }
			
	public void setNamaMakanan (String namaMakanan)
		{ this.namaMakanan = namaMakanan; }
			
	public String getHargaMakanan ()
		{ return hargaMakanan; }
			
	public void setHargaMakanan (String hargaMakanan)
		{ this.hargaMakanan = hargaMakanan; }
			
	public String getFotoMakanan ()
		{ return fotoMakanan; }
			
	public void setFotoMakanan (String fotoMakanan)
		{ this.fotoMakanan = fotoMakanan; }
			
	public void setKey (String key)
		{ this.key = key; }
}
