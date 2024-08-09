public static <T> List<List<T>> groupByField(Collection<T> collection, final String fieldName) {
		return group(collection, new Hash<T>() {
			private List<Object> fieldNameList = new ArrayList<>();

			@Override
			public int hash(T t) {
				if (null == t || false == BeanUtil.isBean(t.getClass())) {
					// 非Bean放在同一子分组中
					return 0;
				}
				final Object value = ReflectUtil.getFieldValue(t, fieldName);
				int hash = fieldNameList.indexOf(value);
				if (hash < 0) {
					fieldNameList.add(value);
					return fieldNameList.size() - 1;
				} else {
					return hash;
				}
			}
		});
	}