public static <T> Set<T> randomEleSet(Collection<T> collection, int count) {
		ArrayList<T> source = new ArrayList<>(new HashSet<>(collection));
		if (count > source.size()) {
			throw new IllegalArgumentException("Count is larger than collection distinct size !");
		}

		final HashSet<T> result = new HashSet<T>(count);
		int limit = collection.size();
		while (result.size() < count) {
			result.add(randomEle(source, limit));
		}

		return result;
	}