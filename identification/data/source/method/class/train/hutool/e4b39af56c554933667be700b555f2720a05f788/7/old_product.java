public static <T> Collection<T> filter(Collection<T> collection, Editor<T> editor) {
		Collection<T> collection2 = ObjectUtil.clone(collection);
		try {
			collection2.clear();
		} catch (UnsupportedOperationException e) {
			//克隆后的对象不支持清空，说明为不可变集合对象，使用默认的ArrayList保存结果
			collection2 = new ArrayList<>();
		}

		T modified;
		for (T t : collection) {
			modified = editor.edit(t);
			if (null != modified) {
				collection2.add(t);
			}
		}
		return collection2;
	}