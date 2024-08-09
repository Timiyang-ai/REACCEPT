public boolean isSameTypeAsResource(Class type) {
		return ReflectUtil.isSubclass(type, resource.getClass());
	}