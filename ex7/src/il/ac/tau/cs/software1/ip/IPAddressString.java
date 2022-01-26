package il.ac.tau.cs.software1.ip;

public class IPAddressString implements IPAddress {
	private String address;
	
	IPAddressString(String address) {
		this.address=address;
	}

	@Override
	public String toString() {
		return address;
	}

	@Override
	public boolean equals(IPAddress other) {
		return address.equals(other.toString());
	}

	@Override
	public int getOctet(int index) {
		return Integer.parseInt(address.split("\\.")[index]);
		}

	@Override
	public boolean isPrivateNetwork(){
		switch (getOctet(0)) {	
		case 10:
			return true;
		case 172:
			if (16 <= getOctet(1) &&  getOctet(1) <= 31)
				return true;
			break;
		case 192:
			if (getOctet(1) == 168)
				return true;
			break;
		case 169:
			if (getOctet(1) == 254)
				return true;
			break;			
		}
		return false;
	}
}
