@Override
	public T remove(Object key) {
		if (key instanceof String) {
			prefs.remove((String) key);
		}
		
		return null;
	}