public static <T> Collection<T> disjunction(final Collection<T> coll1, final Collection<T> coll2) {
		if(isEmpty(coll1)) {
			return coll2;
		}
		if(isEmpty(coll2)) {
			return coll1;
		}
		
		final ArrayList<T> result = new ArrayList<>();
		final Map<T, Integer> map1 = countMap(coll1);
		final Map<T, Integer> map2 = countMap(coll2);
		final Set<T> elts = newHashSet(coll2);
		elts.addAll(coll1);
		int m;
		for (T t : elts) {
			m = Math.abs(Convert.toInt(map1.get(t), 0) - Convert.toInt(map2.get(t), 0));
			for (int i = 0; i < m; i++) {
				result.add(t);
			}
		}
		return result;
	}