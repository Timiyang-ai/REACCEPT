public static <T> Entity parse(T po) {
		return create(null).fromVo(po);
	}