package com.collabera.bccontract.model;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Objects;

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
	
	/**
	 * @param index unique incrementing id for block
	 * @param previousHash hash of prior block in chain
	 * @param data body of text for contract
	 * @param publicKey public rsa key of intended recipient
	 */
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
	
	/**
	 * @return hash of: index, prior block's hash, timestamp, data, and recipient public key
	 */
	public int toHash()
	{
		return Objects.hash(index, previousHash, timestamp, data, publicKey);
	}
	
	//will be used later to check a block for tampering
	/**
	 * @return true if hash field on this block matches what hash should be given other fields
	 */
	public boolean checkHash()
	{
		return (this.hash == toHash());
	}
	
	/**
	 * @return index/id of block
	 */
	public int getIndex()
	{
		return index;
	}
	/**
	 * @param index id of block
	 */
	public void setIndex(int index)
	{
		this.index = index;
		this.hash = toHash();
	}
	/**
	 * @return saved hash of prior block
	 */
	public int getPreviousHash()
	{
		return previousHash;
	}
	/**
	 * @param previousHash hash of prior block
	 */
	public void setPreviousHash(int previousHash)
	{
		this.previousHash = previousHash;
		this.hash = toHash();
	}
	/**
	 * @return timestamp when block was made
	 */
	public Timestamp getTimestamp()
	{
		return timestamp;
	}
	/**
	 * @param timestamp when block was made - cannot be manually set, is updated to current time if null
	 */
	public void setTimestamp(Timestamp timestamp)
	{
		if (this.timestamp == null)
			this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	/**
	 * @return bytes of text body
	 */
	public byte[] getData()
	{
		return data;
	}
	
	/**
	 * @param data text body
	 */
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
	/**
	 * @return currently saved hash for block
	 */
	public int getHash()
	{
		return hash;
	}
	/**
	 * @param hash saves hash of block to hash field - cannot be manually set
	 */
	public void setHash(int hash)
	{
		this.hash = toHash();
	}
	/**
	 * @return rsa pub key of intended recipient
	 */
	public String getPublicKey()
	{
		return publicKey;
	}
	/**
	 * @param publicKey rsa pub key of intended recipient
	 */
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
