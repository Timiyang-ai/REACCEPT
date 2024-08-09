public static String toPrettyString(Object value) {
		if (value == null) {
			return NULL;
		}

		Class<?> type = value.getClass();

		if (type.isArray()) {
			Class componentType = type.getComponentType();

			if (componentType.isPrimitive()) {
				return primitiveArrayToString(value, componentType);
			} else {
				return objectArrayToString(value);
			}
		} else if (value instanceof Iterable) {
			// 因为Collection的处理也是默认调用元素的toString(),
			// 为了处理元素是数组的情况，同样需要重载
			return collectionToString(value);
		}

		return value.toString();
	}