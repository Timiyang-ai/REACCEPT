@Override
	public T remove(Object key) {
		if (key instanceof String) {
			adapter.remove(prefs, (String) key);
		}
		
		return null;
	}