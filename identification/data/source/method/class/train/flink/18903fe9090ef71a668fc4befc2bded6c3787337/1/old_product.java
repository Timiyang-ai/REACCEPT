public int indexOf(BinaryString subStr, int start) {
		ensureMaterialized();
		subStr.ensureMaterialized();
		if (subStr.sizeInBytes == 0) {
			return 0;
		}
		if (inFirstSegment()) {
			// position in byte
			int byteIdx = 0;
			// position is char
			int charIdx = 0;
			while (byteIdx < sizeInBytes && charIdx < start) {
				byteIdx += numBytesForFirstByte(getByteOneSegment(byteIdx));
				charIdx++;
			}
			do {
				if (byteIdx + subStr.sizeInBytes > sizeInBytes) {
					return -1;
				}
				if (SegmentsUtil.equals(segments, offset + byteIdx,
					subStr.segments, subStr.offset, subStr.sizeInBytes)) {
					return charIdx;
				}
				byteIdx += numBytesForFirstByte(getByteOneSegment(byteIdx));
				charIdx++;
			} while (byteIdx < sizeInBytes);

			return -1;
		} else {
			return indexOfSlow(subStr, start);
		}
	}