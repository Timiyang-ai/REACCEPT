public static boolean collectionContains(Collection<?> objects, Object obj) {
		if (obj == null || objects == null) {
			return false;
		}
		
		for (Object o : objects) {
			if (o != null && o.equals(obj)) {
				return true;
			}
		}
		
		return false;
	}