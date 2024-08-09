public static <T> T notNull(T object, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
		if (object == null) {
			throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
		}
		return object;
	}