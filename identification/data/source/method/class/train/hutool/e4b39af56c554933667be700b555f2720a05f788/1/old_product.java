public static <T> List<T> filter(List<T> list, Filter<T> filter) {
		if (null == list || null == filter) {
			return list;
		}
		final List<T> list2 = (list instanceof LinkedList) ? new LinkedList<T>() : new ArrayList<T>(list.size());
		for (T t : list) {
			if (filter.accept(t)) {
				list2.add(t);
			}
		}
		return list2;
	}