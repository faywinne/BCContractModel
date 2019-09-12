package com.collabera.bccontract.model;

import java.nio.charset.Charset;
import java.sql.Timestamp;

public class Block
{
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
		this.data = data;
		this.publicKey = publicKey;
		timestamp = new Timestamp(System.currentTimeMillis());
		
		this.hash = toHash();
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
		return String.format("[index:%d, timestamp:%s, previousHash:%d, publicKey:%s, hash:%d, data:%s]", 
				index, timestamp, previousHash, publicKey, getHash(), new String(data, Charset.defaultCharset()));
	}
}
