public static <T> HashSet<T> newHashSet(boolean isSorted, Enumeration<T> enumration) {
		if (null == enumration) {
			return newHashSet(isSorted, (T[]) null);
		}
		final HashSet<T> set = isSorted ? new LinkedHashSet<T>() : new HashSet<T>();
		while (enumration.hasMoreElements()) {
			set.add(enumration.nextElement());
		}
		return set;
	}