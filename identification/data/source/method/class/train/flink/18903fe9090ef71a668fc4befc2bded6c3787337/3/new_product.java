@Override
	public int compareTo(@Nonnull BinaryString other) {
		if (javaObject != null && other.javaObject != null) {
			return javaObject.compareTo(other.javaObject);
		}

		ensureMaterialized();
		other.ensureMaterialized();
		if (segments.length == 1 && other.segments.length == 1) {

			int len = Math.min(sizeInBytes, other.sizeInBytes);
			MemorySegment seg1 = segments[0];
			MemorySegment seg2 = other.segments[0];

			for (int i = 0; i < len; i++) {
				int res = (seg1.get(offset + i) & 0xFF) - (seg2.get(other.offset + i) & 0xFF);
				if (res != 0) {
					return res;
				}
			}
			return sizeInBytes - other.sizeInBytes;
		}

		// if there are multi segments.
		return compareMultiSegments(other);
	}