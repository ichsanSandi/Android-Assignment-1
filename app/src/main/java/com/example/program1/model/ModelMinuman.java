package com.example.program1.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelMinuman
{
	private String key;
	private String idMinuman;
	private String namaMinuman;
	private String hargaMinuman;
	private String fotoMinuman;
	
	public ModelMinuman ()
		{	}
	
	public ModelMinuman (String idMinuman, String namaMinuman, String hargaMinuman, String fotoMinuman)
	{
		this.idMinuman = idMinuman;
		this.namaMinuman = namaMinuman;
		this.hargaMinuman = hargaMinuman;
		this.fotoMinuman = fotoMinuman;
	}
	
	public String getIdMinuman ()
		{ return idMinuman; }
	
	public void setIdMinuman (String idMinuman)
		{ this.idMinuman = idMinuman; }
	
	public String getNamaMinuman ()
		{ return namaMinuman; }
	
	public void setNamaMinuman (String namaMinuman)
		{ this.namaMinuman = namaMinuman; }
	
	public String getHargaMinuman ()
		{ return hargaMinuman; }
	
	public void setHargaMinuman (String hargaMinuman)
		{ this.hargaMinuman = hargaMinuman; }
	
	public String getFotoMinuman ()
		{ return fotoMinuman; }
	
	public void setFotoMinuman (String fotoMinuman)
		{ this.fotoMinuman = fotoMinuman; }
}
