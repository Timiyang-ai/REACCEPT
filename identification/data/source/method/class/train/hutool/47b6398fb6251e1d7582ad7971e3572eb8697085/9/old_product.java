public static void copyProperties(final Object source, Object target, boolean ignoreCase, CopyOptions copyOptions) {
		if(null == copyOptions){
			copyOptions = new CopyOptions();
		}
		final boolean ignoreError = copyOptions.ignoreError;
		
		final Map<String, PropertyDescriptor> sourcePdMap;
		try {
			sourcePdMap = getFieldNamePropertyDescriptorMap(source.getClass(), ignoreCase);
		} catch (IntrospectionException e) {
			throw new UtilException(e);
		}
		
		fillBean(target, new ValueProvider<String>(){
			@Override
			public Object value(String key, Class<?> valueType) {
				PropertyDescriptor sourcePd = sourcePdMap.get(key);
				Method readMethod = sourcePd.getReadMethod();
				if (readMethod != null && ClassUtil.isAssignable(valueType, readMethod.getReturnType())) {
					try {
						return ClassUtil.setAccessible(readMethod).invoke(source);
					} catch (Exception e) {
						if(false == ignoreError){
							throw new UtilException(e, "Inject [{}] error!", key);
						}
					}
				}
				return null;
			}

			@Override
			public boolean containsKey(String key) {
				return sourcePdMap.containsKey(key);
			}
			
		}, copyOptions);
	}