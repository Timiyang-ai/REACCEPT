@SafeVarargs
	final void addAll(final T... values) {
		if (values.length >= _buffer.length) {
			arraycopy(
				values, values.length - _buffer.length,
				_buffer, 0, _buffer.length
			);
			_size = _buffer.length;
			_index = 0;
		} else {
			final int remaining = _buffer.length - _index;
			_size = min(_size + values.length, _buffer.length);

			if (values.length <= remaining) {
				arraycopy(values, 0, _buffer, _index, values.length);
				_index += values.length;
			} else {
				arraycopy(values, 0, _buffer, _index, remaining);
				arraycopy(values, remaining, _buffer, 0, values.length - remaining);
				_index = values.length - remaining;
			}
		}
	}