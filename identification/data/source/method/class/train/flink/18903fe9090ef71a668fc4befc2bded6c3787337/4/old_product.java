public BinaryString substring(final int start, final int until) {
		ensureMaterialized();
		if (until <= start || start >= sizeInBytes) {
			return EMPTY_UTF8;
		}
		if (inFirstSegment()) {
			MemorySegment segment = segments[0];
			int i = 0;
			int c = 0;
			while (i < sizeInBytes && c < start) {
				i += numBytesForFirstByte(segment.get(i + offset));
				c += 1;
			}

			int j = i;
			while (i < sizeInBytes && c < until) {
				i += numBytesForFirstByte(segment.get(i + offset));
				c += 1;
			}

			if (i > j) {
				byte[] bytes = new byte[i - j];
				segment.get(offset + j, bytes, 0, i - j);
				return fromBytes(bytes);
			} else {
				return EMPTY_UTF8;
			}
		} else {
			return substringSlow(start, until);
		}
	}