public boolean isSameTypeAsResource(final Class type) {
		return ClassUtil.isTypeOf(type, resource.getClass());
	}