public boolean isSameTypeAsResource(Class type) {
		return ClassUtil.isTypeOf(type, resource.getClass());
	}