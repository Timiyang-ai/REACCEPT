@Override
	public T remove(int index) {
		int lastIndex = size() - 1;
		
		copy(index + 1, index, lastIndex - index);
		prefs.remove(key(lastIndex));
		
		return null;
	}