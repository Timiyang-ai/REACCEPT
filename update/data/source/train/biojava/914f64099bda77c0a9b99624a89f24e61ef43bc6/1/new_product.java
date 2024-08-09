public LinkedHashMap<String,S> process() throws IOException {
    	LinkedHashMap<String,S> sequences = process(-1);
    	close();
    	return sequences;
    }