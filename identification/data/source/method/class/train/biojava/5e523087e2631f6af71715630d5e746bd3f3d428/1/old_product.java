public LinkedHashMap<String,S> process() throws IOException, CompoundNotFoundException {
		LinkedHashMap<String,S> sequences = process(-1);
		return sequences;
	}