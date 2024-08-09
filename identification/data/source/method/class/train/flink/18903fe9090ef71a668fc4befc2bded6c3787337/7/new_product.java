public int indexOf(BinaryString str, int fromIndex) {
		ensureMaterialized();
		str.ensureMaterialized();
		if (str.binarySection.sizeInBytes == 0) {
			return 0;
		}
		if (inFirstSegment()) {
			// position in byte
			int byteIdx = 0;
			// position is char
			int charIdx = 0;
			while (byteIdx < binarySection.sizeInBytes && charIdx < fromIndex) {
				byteIdx += numBytesForFirstByte(getByteOneSegment(byteIdx));
				charIdx++;
			}
			do {
				if (byteIdx + str.binarySection.sizeInBytes > binarySection.sizeInBytes) {
					return -1;
				}
				if (SegmentsUtil.equals(binarySection.segments, binarySection.offset + byteIdx,
						str.binarySection.segments, str.binarySection.offset, str.binarySection.sizeInBytes)) {
					return charIdx;
				}
				byteIdx += numBytesForFirstByte(getByteOneSegment(byteIdx));
				charIdx++;
			} while (byteIdx < binarySection.sizeInBytes);

			return -1;
		} else {
			return indexOfMultiSegs(str, fromIndex);
		}
	}