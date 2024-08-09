private void add(final Object[] values, final int start, final int length) {
		if (length >= _buffer.length) {
			arraycopy(
				values, values.length - _buffer.length + start,
				_buffer, 0, _buffer.length
			);
			_size = _buffer.length;
			_index = 0;
		} else {
			final int remaining = _buffer.length - _index;
			_size = min(_size + length, _buffer.length);

			if (length <= remaining) {
				arraycopy(values, start, _buffer, _index, length);
				_index += length;
			} else {
				arraycopy(values, start, _buffer, _index, remaining);
				arraycopy(
					values, remaining + start,
					_buffer, 0, length - remaining
				);
				_index = length - remaining;
			}
		}
	}