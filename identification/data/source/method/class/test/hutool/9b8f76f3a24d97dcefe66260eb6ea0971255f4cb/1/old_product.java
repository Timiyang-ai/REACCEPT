public static Map<String, Object> beanToMap(Object bean, Map<String, Object> targetMap, final boolean isToUnderlineCase, boolean ignoreNullValue) {
		if (bean == null) {
			return null;
		}

		return beanToMap(bean, targetMap, ignoreNullValue, new Editor<String>() {

			@Override
			public String edit(String key) {
				return isToUnderlineCase ? StrUtil.toUnderlineCase(key) : key;
			}
		});
	}