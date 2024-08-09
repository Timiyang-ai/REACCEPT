public int compareTo(DoubleRange other) {
		int temp = low.compareTo(other.low);
		if (temp == 0) {
			temp = other.high.compareTo(high); 
		}
		return temp;
 	}