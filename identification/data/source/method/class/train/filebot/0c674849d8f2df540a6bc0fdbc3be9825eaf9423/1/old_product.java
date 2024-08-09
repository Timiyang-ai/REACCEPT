@Override
	public void add(int index, T element) {
		copy(index, index + 1, size() - index);
		
		setImpl(index, element);
	}