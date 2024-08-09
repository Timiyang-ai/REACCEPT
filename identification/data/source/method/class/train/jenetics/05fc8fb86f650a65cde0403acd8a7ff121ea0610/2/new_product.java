public int toByteArray(final byte[] bytes) {
    	if (bytes.length < _genes.length) {
    		throw new IndexOutOfBoundsException(); 
    	}
    	
    	System.arraycopy(_genes, 0, bytes, 0, _genes.length);
    	
    	return _genes.length;
    }