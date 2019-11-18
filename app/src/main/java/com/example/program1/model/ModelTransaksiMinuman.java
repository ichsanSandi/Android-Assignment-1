package com.example.program1.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelTransaksiMinuman
{
	private String key;
	private String idTransaksiMinuman;
	private String namaMinuman;
	private String hargaMinuman;
	private String namaKonsumen;
	private String jumlahMinuman;
	private String statusMinuman;
	private int timestamp;
	
	public ModelTransaksiMinuman ()
		{ }
	
	public ModelTransaksiMinuman (String idTransaksiMinuman, String namaMinuman, String hargaMinuman, String namaKonsumen, String jumlahMinuman, String statusMinuman, int timestamp)
	{
		this.idTransaksiMinuman = idTransaksiMinuman;
		this.namaMinuman = namaMinuman;
		this.hargaMinuman = hargaMinuman;
		this.namaKonsumen = namaKonsumen;
		this.jumlahMinuman = jumlahMinuman;
		this.statusMinuman = statusMinuman;
		this.timestamp = timestamp;
	}
	
	public String getIdTransaksiMinuman ()
		{ return idTransaksiMinuman; }
	
	public void setIdTransaksiMinuman (String idTransaksiMinuman)
		{ this.idTransaksiMinuman = idTransaksiMinuman; }
	
	public String getNamaMinuman ()
		{ return namaMinuman; }
	
	public void setNamaMinuman (String namaMinuman)
		{ this.namaMinuman = namaMinuman; }
		
	public String getHargaMinuman ()
		{ return hargaMinuman; }
	
	public void setHargaMinuman (String hargaMinuman)
		{ this.hargaMinuman = hargaMinuman; }
	
	public String getNamaKonsumen ()
		{ return namaKonsumen; }
	
	public void setNamaKonsumen (String namaKonsumen)
		{ this.namaKonsumen = namaKonsumen; }
	
	public String getJumlahMinuman ()
		{ return jumlahMinuman; }
	
	public void setJumlahMinuman (String jumlahMinuman)
		{ this.jumlahMinuman = jumlahMinuman; }
		
	public String getStatusMinuman ()
		{ return statusMinuman; }
	
	public void setStatusMinuman (String statusMinuman)
		{ this.statusMinuman = statusMinuman; }
	
	public void setKey (String key)
		{ this.key = key; }
	
	public int getTimestamp ()
		{ return timestamp; }
	
	public void setTimestamp (int timestamp)
		{ this.timestamp = timestamp; }
	
	public String getKey ()
		{ return key; }
}
