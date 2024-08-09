public int indexOf(BinaryString str, int fromIndex) {
		ensureMaterialized();
		str.ensureMaterialized();
		if (str.sizeInBytes == 0) {
			return 0;
		}
		if (inFirstSegment()) {
			// position in byte
			int byteIdx = 0;
			// position is char
			int charIdx = 0;
			while (byteIdx < sizeInBytes && charIdx < fromIndex) {
				byteIdx += numBytesForFirstByte(getByteOneSegment(byteIdx));
				charIdx++;
			}
			do {
				if (byteIdx + str.sizeInBytes > sizeInBytes) {
					return -1;
				}
				if (SegmentsUtil.equals(segments, offset + byteIdx,
						str.segments, str.offset, str.sizeInBytes)) {
					return charIdx;
				}
				byteIdx += numBytesForFirstByte(getByteOneSegment(byteIdx));
				charIdx++;
			} while (byteIdx < sizeInBytes);

			return -1;
		} else {
			return indexOfMultiSegs(str, fromIndex);
		}
	}