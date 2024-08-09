public static <T> HashSet<T> newHashSet(boolean isSorted, Iterator<T> iter) {
		if (null == iter) {
			return newHashSet(isSorted, (T[]) null);
		}
		final HashSet<T> set = isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
		while (iter.hasNext()) {
			set.add(iter.next());
		}
		return set;
	}