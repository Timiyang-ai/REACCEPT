public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<T> collection) {
		return isSorted ? new LinkedHashSet<T>(collection) : new HashSet<T>(collection);
	}