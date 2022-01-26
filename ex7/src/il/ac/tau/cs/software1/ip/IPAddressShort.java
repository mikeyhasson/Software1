package il.ac.tau.cs.software1.ip;

import java.util.Arrays;

public class IPAddressShort implements IPAddress {
	private short[] address;
	
	IPAddressShort(short[] address) {
		this.address=Arrays.copyOf(address, address.length);
	}

	@Override
	public String toString() {
		String s="";
		for (short dig: address)
			s+=Short.toString(dig) + '.';
		return s.substring(0, s.length()-1);
	}

	@Override
	public boolean equals(IPAddress other) {
		for (int i=0;i<address.length;i++) {
			if (address[i]!=other.getOctet(i))
				return false;
		}
		return true;
	}

	@Override
	public int getOctet(int index) {
		return address[index];
	}

	@Override
	public boolean isPrivateNetwork(){
		switch (address[0]) {
		case 10:
			return true;
		case 172:
			if (16 <= address[1] &&  address[1] <= 31)
				return true;
			break;
		case 192:
			if (address[1] == 168)
				return true;
			break;
		case 169:
			if (address[1] == 254)
				return true;
			break;			
		}
		return false;
	}
	
}
