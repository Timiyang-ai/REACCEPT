public boolean isSameTypeAsResource(Class type) {
		return ReflectUtil.isTypeOf(type, resource.getClass());
	}