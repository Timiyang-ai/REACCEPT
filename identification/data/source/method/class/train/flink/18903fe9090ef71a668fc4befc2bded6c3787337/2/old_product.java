public boolean contains(final BinaryString s) {
		ensureMaterialized();
		s.ensureMaterialized();
		if (s.sizeInBytes == 0) {
			return true;
		}
		int find = SegmentsUtil.find(
			segments, offset, sizeInBytes,
			s.segments, s.offset, s.sizeInBytes);
		return find != -1;
	}