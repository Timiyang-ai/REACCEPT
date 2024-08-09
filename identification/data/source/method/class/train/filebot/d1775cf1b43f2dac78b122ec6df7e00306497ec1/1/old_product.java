@Override
	public T remove(int index) {
		
		int lastIndex = size() - 1;
		
		List<T> shiftList = new ArrayList<T>(subList(index, lastIndex + 1));
		
		T value = shiftList.remove(0);
		
		prefs.remove(key(lastIndex));
		
		for (T element : shiftList) {
			set(index, element);
			index++;
		}
		
		return value;
	}