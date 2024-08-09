public static <T> List<T> filter(List<T> list, Editor<T> editor) {
		if (null == list || null == editor) {
			return list;
		}

		final List<T> list2 = (list instanceof LinkedList) ? new LinkedList<T>() : new ArrayList<T>(list.size());
		T modified;
		for (T t : list) {
			modified = editor.edit(t);
			if (null != modified) {
				list2.add(modified);
			}
		}
		return list2;
	}