public static <T> Set<T> randomEleSet(Collection<T> collection, int count) {
		final ArrayList<T> source = CollUtil.distinct(collection);
		if (count > source.size()) {
			throw new IllegalArgumentException("Count is larger than collection distinct size !");
		}

		final HashSet<T> result = new HashSet<T>(count);
		int limit = source.size();
		while (result.size() < count) {
			result.add(randomEle(source, limit));
		}

		return result;
	}