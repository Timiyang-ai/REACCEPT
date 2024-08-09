@SafeVarargs
	public static <T> HashSet<T> newHashSet(boolean isSorted, T... ts) {
		if (null == ts) {
			return isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
		}
		int initialCapacity = Math.max((int) (ts.length / .75f) + 1, 16);
		final HashSet<T> set = isSorted ? new LinkedHashSet<T>(initialCapacity) : new HashSet<T>(initialCapacity);
		Collections.addAll(set, ts);
		return set;
	}