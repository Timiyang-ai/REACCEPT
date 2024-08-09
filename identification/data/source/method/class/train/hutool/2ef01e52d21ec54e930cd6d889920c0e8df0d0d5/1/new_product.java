public static ByteBuffer copy(ByteBuffer src, ByteBuffer dest) {
		return copy(src, dest, Math.min(src.limit(), dest.remaining()));
	}