@Override
	public int compareTo(@Nonnull BinaryString other) {
		if (javaObject != null && other.javaObject != null) {
			return javaObject.compareTo(other.javaObject);
		}

		ensureMaterialized();
		other.ensureMaterialized();
		if (binarySection.segments.length == 1 && other.binarySection.segments.length == 1) {

			int len = Math.min(binarySection.sizeInBytes, other.binarySection.sizeInBytes);
			MemorySegment seg1 = binarySection.segments[0];
			MemorySegment seg2 = other.binarySection.segments[0];

			for (int i = 0; i < len; i++) {
				int res =
					(seg1.get(binarySection.offset + i) & 0xFF) - (seg2.get(other.binarySection.offset + i) & 0xFF);
				if (res != 0) {
					return res;
				}
			}
			return binarySection.sizeInBytes - other.binarySection.sizeInBytes;
		}

		// if there are multi segments.
		return compareMultiSegments(other);
	}