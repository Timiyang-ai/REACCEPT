public static Point QuadKeyToTileXY(final String quadKey, final Point reuse) {
		final Point out = (reuse == null ? new Point() : reuse);
		int tileX = 0;
		int tileY = 0;

		final int levelOfDetail = quadKey.length();
		for (int i = levelOfDetail; i > 0; i--) {
			final int mask = 1 << (i - 1);
			switch (quadKey.charAt(levelOfDetail - i)) {
			case '0':
				break;

			case '1':
				tileX |= mask;
				break;

			case '2':
				tileY |= mask;
				break;

			case '3':
				tileX |= mask;
				tileY |= mask;
				break;

			default:
				throw new IllegalArgumentException("Invalid QuadKey digit sequence.");
			}
		}
		out.set(tileX, tileY);
		return out;
	}