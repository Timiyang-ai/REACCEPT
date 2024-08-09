public static String TileXYToQuadKey(final int tileX, final int tileY, final int levelOfDetail) {
		final char[] quadKey = new char[levelOfDetail];
		for (int i = 0 ; i < levelOfDetail;  i++) {
			char digit = '0';
			final int mask = 1 << i;
			if ((tileX & mask) != 0) {
				digit++;
			}
			if ((tileY & mask) != 0) {
				digit++;
				digit++;
			}
			quadKey[levelOfDetail - i - 1] = digit;
		}
		return new String(quadKey);
	}