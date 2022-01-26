package il.ac.tau.cs.software1.ip;

import java.nio.ByteBuffer;

public class IPAddressInt implements IPAddress {
	private int address;
	
	IPAddressInt(int address) {
		this.address=address;
		}

	@Override
	public String toString() {
		ByteBuffer buffer=  ByteBuffer.allocate(4).putInt(address);
		String s="";
		for (byte i:buffer.array()) {
			s+=(int)(i & 0xFF) + ".";
		}
		return s.substring(0,s.length()-1);
	}

	@Override
	public boolean equals(IPAddress other) {
		int [] otherIpInts=new int[4];
		for (int i=0;i <4;i++) {
			otherIpInts[i]=other.getOctet(i);
		}
		return ipToInt(otherIpInts) == address;
	}

	@Override
	public int getOctet(int index) {
		ByteBuffer buffer=  ByteBuffer.allocate(4).putInt(address);
		return (int)(buffer.get(index) & 0xFF);
	}

	@Override
	public boolean isPrivateNetwork(){
		if (address >= ipToInt(10,0,0,0) && address <= ipToInt(10,255,255,255)) 
			return true; //between 10.0.0.0-10.255.255.255
		
		if (address >= ipToInt(172,16,0,0) && address <= ipToInt(172,31,255,255))
			return true;//between  172.16.0.0  -172.31.255.255
		
		if (address >= ipToInt(192,168,0,0) && address <= ipToInt(192,168,255,255))
			return true;//between  192.168.0.0 -192.168.255.255

		if (address >= ipToInt(169,254,0,0) && address <= ipToInt(169,254,255,255))
			return true;//between  169.254.0.0 -169.254.255.255

		return false;
	}

	private static int ipToInt(int ...nums) {
		ByteBuffer buffer=  ByteBuffer.allocate(4);
		int j=0;
		for (int i: nums) {
			buffer.put(j,(byte)i);
			j++;
		}

		return buffer.getInt();
	}
	
}
