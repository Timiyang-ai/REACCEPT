public boolean isSameTypeAsResource(Class type) {
		return ReflectUtil.isClassOf(type, resource.getClass());
	}