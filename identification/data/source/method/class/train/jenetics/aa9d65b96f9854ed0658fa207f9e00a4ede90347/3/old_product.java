<A> A[] toArray(final IntFunction<A[]> generator) {
		final A[] result = generator.apply(_size);
		if (_size < _buffer.length || _index == 0) {
			arraycopy(_buffer, 0, result, 0, _size);
		} else {
			arraycopy(_buffer, _index, result, 0, _buffer.length - _index);
			arraycopy(_buffer, 0, result, _buffer.length - _index, _index);
		}

		return result;
	}