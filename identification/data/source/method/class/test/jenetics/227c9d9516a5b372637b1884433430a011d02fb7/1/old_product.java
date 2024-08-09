public static <C extends Comparable<? super C>> 
	Histogram<C> valueOf(final C... classes) {
		return new Histogram<C>(classes);
	}