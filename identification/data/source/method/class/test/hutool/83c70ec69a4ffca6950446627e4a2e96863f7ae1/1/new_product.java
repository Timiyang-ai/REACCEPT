public static void isNull(Object object, String errorMsgTemplate, Object... params) throws IllegalArgumentException {
		if (object != null) {
			throw new IllegalArgumentException(StrUtil.format(errorMsgTemplate, params));
		}
	}