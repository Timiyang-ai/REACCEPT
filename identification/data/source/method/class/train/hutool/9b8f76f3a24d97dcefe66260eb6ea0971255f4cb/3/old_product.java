public static Map<String, Object> beanToMap(Object bean, boolean isToUnderlineCase, boolean ignoreNullValue) {

		if (bean == null) {
			return null;
		}
		final Map<String, Object> map = new HashMap<String, Object>();

		final Collection<PropDesc> props = BeanUtil.getBeanDesc(bean.getClass()).getProps();
		String key;
		Method getter;
		Object value;
		for (PropDesc prop : props) {
			key = prop.getFieldName();
			// 过滤class属性
			// 得到property对应的getter方法
			getter = prop.getGetter();
			if (null != getter) {
				// 只读取有getter方法的属性
				try {
					value = getter.invoke(bean);
				} catch (Exception ignore) {
					continue;
				}
				if (false == ignoreNullValue || (null != value && false == value.equals(bean))) {
					map.put(isToUnderlineCase ? StrUtil.toUnderlineCase(key) : key, value);
				}
			}
		}
		return map;
	}