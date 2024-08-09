public static <T> Collection<T> filter(Collection<T> collection, Filter<T> filter) {
		Collection<T> collection2 = ObjectUtil.clone(collection);
		collection2.clear();

		for (T t : collection) {
			if (filter.accept(t)) {
				collection2.add(t);
			}
		}
		return collection2;
	}