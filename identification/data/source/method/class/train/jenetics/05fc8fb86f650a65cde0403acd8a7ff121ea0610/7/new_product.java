public byte[] toByteArray() {
    	final byte[] data = new byte[_genes.length];
    	toByteArray(data);
    	return data;
    }