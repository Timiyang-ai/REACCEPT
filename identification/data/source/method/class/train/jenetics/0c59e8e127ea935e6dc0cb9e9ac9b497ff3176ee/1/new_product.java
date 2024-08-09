public void add(final T value) {
		_buffer[_index] = value;

		if (++_index == _buffer.length) {
			_index = 0;
		}
		if (_size < _buffer.length) {
			++_size;
		}
	}