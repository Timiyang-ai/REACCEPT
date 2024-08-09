public static <T> List<List<T>> group(Collection<T> collection, Hash<T> hash) {
		final List<List<T>> result = new ArrayList<>();
		if (isEmpty(collection)) {
			return result;
		}
		if (null == hash) {
			// 默认hash算法，按照元素的hashCode分组
			hash = t -> (null == t) ? 0 : t.hashCode();
		}

		int index;
		List<T> subList;
		for (T t : collection) {
			index = hash.hash(t);
			if (result.size() - 1 < index) {
				while (result.size() - 1 < index) {
					result.add(null);
				}
				result.set(index, newArrayList(t));
			} else {
				subList = result.get(index);
				if (null == subList) {
					result.set(index, newArrayList(t));
				} else {
					subList.add(t);
				}
			}
		}
		return result;
	}