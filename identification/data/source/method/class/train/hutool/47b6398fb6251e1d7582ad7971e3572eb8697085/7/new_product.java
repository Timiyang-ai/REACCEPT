public static void copyProperties(Object source, Object target, CopyOptions copyOptions) {
		if(null == copyOptions){
			copyOptions = new CopyOptions();
		}
		
		Class<?> actualEditable = target.getClass();
		if (copyOptions.editable != null) {
			//检查限制类是否为target的父类或接口
			if (!copyOptions.editable.isInstance(target)) {
				throw new IllegalArgumentException(StrUtil.format("Target class [{}] not assignable to Editable class [{}]", target.getClass().getName(), copyOptions.editable.getName()));
			}
			actualEditable = copyOptions.editable;
		}
		PropertyDescriptor[] targetPds = null;
		Map<String, PropertyDescriptor> sourcePdMap;
		try {
			sourcePdMap = getFieldNamePropertyDescriptorMap(source.getClass());
			targetPds = getPropertyDescriptors(actualEditable);
		} catch (IntrospectionException e) {
			throw new UtilException(e);
		}
		
		HashSet<String> ignoreSet = copyOptions.ignoreProperties != null ? CollectionUtil.newHashSet(copyOptions.ignoreProperties) : null;
		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreSet == null || false == ignoreSet.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = sourcePdMap.get(targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					// 源对象字段的getter方法返回值必须可转换为目标对象setter方法的第一个参数
					if (readMethod != null && ClassUtil.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							Object value = ClassUtil.setAccessible(readMethod).invoke(source);
							if(null != value || false == copyOptions.isIgnoreNullValue){
								ClassUtil.setAccessible(writeMethod).invoke(target, value);
							}
						} catch (Throwable ex) {
							if(copyOptions.isIgnoreError){
								StaticLog.warn("Copy property [{}] to [{}] error: {}", sourcePd.getName(), targetPd.getName(), ex.getMessage());
								continue;
							}
							throw new UtilException(ex, "Copy property [{}] to [{}] error: {}", sourcePd.getName(), targetPd.getName(), ex.getMessage());
						}
					}
				}
			}
		}
	}