public int compareTo(DoubleRange other) {
		int temp = low.compareTo(other.low);
		if (temp == 0) {
			temp = other.high.compareTo(high); 
		}
		log.debug(this + (temp < 0 ? " < " : (temp > 0 ? " > " : " = ")) + other);
		return temp;
 	}