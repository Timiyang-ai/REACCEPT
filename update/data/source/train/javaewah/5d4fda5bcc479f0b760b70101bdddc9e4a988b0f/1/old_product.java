public static IteratingRLW and(final int bufsize, final IteratingRLW... al) {
		if (al.length == 0)
			throw new IllegalArgumentException("Need at least one iterator");
		if (al.length == 1)
			return al[0];
		final LinkedList<IteratingRLW> basell = new LinkedList<IteratingRLW>();
		for (IteratingRLW i : al) 
			basell.add(i);
		return new BufferedIterator(new AndIt(basell,bufsize));
	}