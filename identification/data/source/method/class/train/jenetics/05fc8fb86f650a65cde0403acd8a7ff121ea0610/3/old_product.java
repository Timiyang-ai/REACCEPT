public int toByteArray(byte[] bytes) {
    	int bytesLength = (length() >> 3 + 1);
    	if (bytes.length < bytesLength) {
    		throw new IndexOutOfBoundsException(); 
    	}

    	Arrays.fill(bytes, 0, bytes.length, (byte)0);
    	for (int i = 0; i < _length; ++i) {
    		BitUtils.setBit(
    			bytes, i, getGene(i).booleanValue()
    		);
    	}
    	
    	return bytesLength;
    }