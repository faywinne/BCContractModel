package com.collabera.bccontract;

import java.nio.charset.Charset;

import com.collabera.bccontract.model.Block;

public class tester
{
	
	public static void main(String[] args)
	{
		Block block = new Block(0,123, "abc".getBytes(Charset.defaultCharset()), "mykey");
		System.out.println(block);
	}
	
}
