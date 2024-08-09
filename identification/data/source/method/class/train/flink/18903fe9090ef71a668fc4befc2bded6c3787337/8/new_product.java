public BinaryString substring(int beginIndex, int endIndex) {
		ensureMaterialized();
		if (endIndex <= beginIndex || beginIndex >= binarySection.sizeInBytes) {
			return EMPTY_UTF8;
		}
		if (inFirstSegment()) {
			MemorySegment segment = binarySection.segments[0];
			int i = 0;
			int c = 0;
			while (i < binarySection.sizeInBytes && c < beginIndex) {
				i += numBytesForFirstByte(segment.get(i + binarySection.offset));
				c += 1;
			}

			int j = i;
			while (i < binarySection.sizeInBytes && c < endIndex) {
				i += numBytesForFirstByte(segment.get(i + binarySection.offset));
				c += 1;
			}

			if (i > j) {
				byte[] bytes = new byte[i - j];
				segment.get(binarySection.offset + j, bytes, 0, i - j);
				return fromBytes(bytes);
			} else {
				return EMPTY_UTF8;
			}
		} else {
			return substringMultiSegs(beginIndex, endIndex);
		}
	}