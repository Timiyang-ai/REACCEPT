public boolean contains(final BinaryString s) {
		ensureMaterialized();
		s.ensureMaterialized();
		if (s.binarySection.sizeInBytes == 0) {
			return true;
		}
		int find = SegmentsUtil.find(
			binarySection.segments, binarySection.offset, binarySection.sizeInBytes,
			s.binarySection.segments, s.binarySection.offset, s.binarySection.sizeInBytes);
		return find != -1;
	}