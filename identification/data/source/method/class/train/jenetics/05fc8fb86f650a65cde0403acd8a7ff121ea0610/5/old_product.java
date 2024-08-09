public byte[] toByteArray() {
    	final byte[] data = new byte[length() >> 3 + 1];
    	final int length = toByteArray(data);
    	assert (length == data.length);
    	return data;
    }