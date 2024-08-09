public static List<Object> getFieldValues(Iterable<?> collection, final String fieldName, boolean ignoreNull) {
		return extract(collection, new Editor<Object>() {
			@Override
			public Object edit(Object bean) {
				if (bean instanceof Map) {
					return ((Map<?, ?>) bean).get(fieldName);
				} else {
					return ReflectUtil.getFieldValue(bean, fieldName);
				}
			}
		}, ignoreNull);
	}