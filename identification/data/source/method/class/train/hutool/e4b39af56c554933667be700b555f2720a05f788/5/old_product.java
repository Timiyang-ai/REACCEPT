public static <T> Collection<T> filter(Collection<T> collection, Editor<T> editor) {
		Collection<T> collection2 = ObjectUtil.clone(collection);
		collection2.clear();

		T modified;
		for (T t : collection) {
			modified = editor.edit(t);
			if (null != modified) {
				collection2.add(t);
			}
		}
		return collection2;
	}