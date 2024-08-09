public int toByteArray(final byte[] bytes) {
    	final int bytesLength = (int)Math.ceil(length()/8.0);
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