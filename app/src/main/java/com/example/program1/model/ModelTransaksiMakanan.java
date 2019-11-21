package com.example.program1.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class ModelTransaksiMakanan
{
	private String key;
	private String idTransaksiMakanan;
	private String namaMakanan;
	private String hargaMakanan;
	private String namaKonsumen;
	private String jumlahMakanan;
	private String statusMakanan;
	private int timestamp;
	
	public ModelTransaksiMakanan () {	}
	
	public ModelTransaksiMakanan (String idTransaksiMakanan, String namaMakanan, String hargaMakanan, String namaKonsumen, String jumlahMakanan, String statusMakanan, int timestamp)
	{
		this.idTransaksiMakanan = idTransaksiMakanan;
		this.namaMakanan = namaMakanan;
		this.hargaMakanan = hargaMakanan;
		this.namaKonsumen = namaKonsumen;
		this.jumlahMakanan = jumlahMakanan;
		this.statusMakanan = statusMakanan;
		this.timestamp = timestamp;
	}
	
	public String getIdTransaksiMakanan () { return idTransaksiMakanan; }
	
	public void setIdTransaksiMakanan (String idTransaksiMakanan) { this.idTransaksiMakanan = idTransaksiMakanan; }
	
	public String getNamaMakanan () { return namaMakanan; }
	
	public void setNamaMakanan (String namaMakanan) { this.namaMakanan = namaMakanan; }
	
	public String getHargaMakanan () { return hargaMakanan; }
	
	public void setHargaMakanan (String hargaMakanan) { this.hargaMakanan = hargaMakanan; }
	
	public String getNamaKonsumen () { return namaKonsumen; }
	
	public void setNamaKonsumen (String namaKonsumen) { this.namaKonsumen = namaKonsumen; }
		
	public String getJumlahMakanan () { return jumlahMakanan; }
	
	public void setJumlahMakanan (String jumlahMakanan) { this.jumlahMakanan = jumlahMakanan; }
	
	public String getStatusMakanan () { return statusMakanan; }
	
	public void setStatusMakanan (String statusMakanan) { this.statusMakanan = statusMakanan; }
	
	public void setKey (String key) { this.key = key; }
	
	public void setTimestamp (int timestamp) { this.timestamp = timestamp; }
	
	public String getKey () { return key; }
	
	public int getTimestamp () { return timestamp; }
}
