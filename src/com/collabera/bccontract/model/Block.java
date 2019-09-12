package com.collabera.bccontract.model;

import java.nio.charset.Charset;
import java.sql.Timestamp;

public class Block
{
	private static final int DATALENGTH = 100;
	//max length for data (body)
	//in a robust implementation would make this decided programmatically
	
	private int index;
	private int previousHash;
	private Timestamp timestamp;
	private byte[] data;
	private int hash;
	private String publicKey;
	
	public Block()
	{
		timestamp = new Timestamp(System.currentTimeMillis());
	}	
	
	public Block(int index, int previousHash, byte[] data, String publicKey)
	{
		super();
		this.index = index;
		this.previousHash = previousHash;
		
		this.publicKey = publicKey;
		timestamp = new Timestamp(System.currentTimeMillis());
		setData(data);
		
		//setData will hash at the end
//		this.hash = toHash();
	}
	
	public int toHash()
	{
		//to implement
		return -1;
	}
	
	public int getIndex()
	{
		return index;
	}
	public void setIndex(int index)
	{
		this.index = index;
		this.hash = toHash();
	}
	public int getPreviousHash()
	{
		return previousHash;
	}
	public void setPreviousHash(int previousHash)
	{
		this.previousHash = previousHash;
		this.hash = toHash();
	}
	public Timestamp getTimestamp()
	{
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp)
	{
		if (this.timestamp == null)
			this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	public byte[] getData()
	{
		return data;
	}
	public void setData(byte[] data)
	{
		this.data = new byte[DATALENGTH];
		
		//iterator to be used for two loops - copying data, and filling to full length
		int i = 0;
		
		//copy bytes to this data block, but only up to cap datalength
		while( i<data.length && i<DATALENGTH)
		{
			this.data[i] = data[i];
			i++;
		}
		
		//now fill rest of data body/intialize with byte 0
		while (i < DATALENGTH)
		{
			this.data[i]=0;
			i++;
		}
		
		
		this.data = data;
		this.hash = toHash();
	}
	public int getHash()
	{
		return hash;
	}
	public void setHash(int hash)
	{
		this.hash = toHash();
	}
	public String getPublicKey()
	{
		return publicKey;
	}
	public void setPublicKey(String publicKey)
	{
		this.publicKey = publicKey;
		this.hash = toHash();
	}
	
	public String toString ()
	{
		return String.format("[index:%d, timestamp:\"%s\", previousHash:%d, publicKey:\"%s\", hash:%d, data:\"%s\"]", 
				index, timestamp, previousHash, publicKey, getHash(), new String(data, Charset.defaultCharset()));
	}
}
