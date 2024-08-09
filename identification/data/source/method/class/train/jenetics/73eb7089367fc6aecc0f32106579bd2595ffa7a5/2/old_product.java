public static void swap(
		final byte[] data, final int start, final int end,
		final byte[] otherData, final int otherStart
	) {
		int index = 0;
		int otherIndex = 0;
		boolean temp = false;

		for (int i = (end - start); --i >= 0;) {
			index = i + start;
			otherIndex = i + otherStart;

			temp = (data[index >>> 3] & (1 << (index & 7))) != 0;
			if ((otherData[otherIndex >>> 3] & (1 << (otherIndex & 7))) != 0) {
				data[index >>> 3] |= 1 << (index & 7);
			} else {
				data[index >>> 3] &= ~(1 << (index & 7));
			}
			if (temp) {
				otherData[otherIndex >>> 3] |= 1 << (otherIndex & 7);
			} else {
				otherData[otherIndex >>> 3] &= ~(1 << (otherIndex & 7));
			}

			//final boolean temp = get(data, i + start);
			//set(data, i + start, get(otherData, otherStart + i));
			//set(otherData, otherStart + i, temp);
		}
	}