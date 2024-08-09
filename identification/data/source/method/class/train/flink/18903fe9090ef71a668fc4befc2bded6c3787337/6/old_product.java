public boolean contains(final BinaryString substring) {
		ensureMaterialized();
		substring.ensureMaterialized();
		if (substring.sizeInBytes == 0) {
			return true;
		}
		int find = SegmentsUtil.find(
			segments, offset, sizeInBytes,
			substring.segments, substring.offset, substring.sizeInBytes);
		return find != -1;
	}