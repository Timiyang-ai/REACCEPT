public static <T> HashSet<T> newHashSet(boolean isSorted, Enumeration<T> enumeration) {
		if (null == enumeration) {
			return newHashSet(isSorted, (T[]) null);
		}
		final HashSet<T> set = isSorted ? new LinkedHashSet<>() : new HashSet<>();
		while (enumeration.hasMoreElements()) {
			set.add(enumeration.nextElement());
		}
		return set;
	}