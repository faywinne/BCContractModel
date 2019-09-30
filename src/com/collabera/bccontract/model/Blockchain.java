package com.collabera.bccontract.model;

import java.util.ArrayList;
import java.util.List;

public class Blockchain
{
	List<Block> blocks;
	public static final String PUBLIC = "$nullkey$";
	
	public Blockchain()
	{
		blocks = new ArrayList<Block>();
	}
	
	/**
	 * @param data body of text to offer information on peerlist and users/keys
	 * @return block constructed to be head of hcain
	 */
	public Block buildFirstBlock(byte[] data)
	{
		//first block has no prior block's hash
		//placeholder public key because data will not be RSA encrypted as other data would
		//data is expected to hold information such as user list to be publicly available
		blocks = new ArrayList<Block>();
		Block block=new Block(0,0,data,PUBLIC);
		blocks.add(block);
		
		return block;
	}
	
	/**
	 * @return last block on the chain
	 */
	public Block getLast()
	{
		return blocks.get(blocks.size()-1);
	}
}
