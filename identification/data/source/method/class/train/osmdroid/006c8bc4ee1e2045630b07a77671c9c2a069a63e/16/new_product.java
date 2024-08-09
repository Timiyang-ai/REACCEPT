public static Point QuadKeyToTileXY(final String quadKey, final Point reuse) {
		final Point out = reuse == null ? new Point() : reuse;
		if (quadKey == null || quadKey.length() == 0) {
			throw new IllegalArgumentException("Invalid QuadKey: " + quadKey);
		}
		int tileX = 0;
		int tileY = 0;
		final int zoom = quadKey.length();
		for (int i = 0 ; i < zoom;  i++) {
			final int value = 1 << i;
			switch (quadKey.charAt(zoom - i - 1)) {
				case '0':
					break;
				case '1':
					tileX += value;
					break;
				case '2':
					tileY += value;
					break;
				case '3':
					tileX += value;
					tileY += value;
					break;
				default:
					throw new IllegalArgumentException("Invalid QuadKey: " + quadKey);
			}
		}
		out.x = tileX;
		out.y = tileY;
		return out;
	}