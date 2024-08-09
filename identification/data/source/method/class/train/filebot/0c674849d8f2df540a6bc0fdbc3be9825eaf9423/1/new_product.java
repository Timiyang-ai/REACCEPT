@Override
	public void add(int index, T element) {
		int size = size();
		
		if (index > size)
			throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
		
		copy(index, index + 1, size - index);
		
		setImpl(index, element);
	}