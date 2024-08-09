public byte[] toByteArray() {
    	final int bytesLength = (int)Math.ceil(length()/8.0);
    	final byte[] data = new byte[bytesLength];
    	final int length = toByteArray(data);
    	assert (length == data.length);
    	return data;
    }