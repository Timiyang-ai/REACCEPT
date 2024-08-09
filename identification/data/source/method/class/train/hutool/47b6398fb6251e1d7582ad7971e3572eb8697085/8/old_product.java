private static void copyProperties(Object source, Object target, Class<?> editable, String... ignoreProperties) {
		Class<?> actualEditable = target.getClass();
		if (editable != null) {
			//检查限制类是否为target的父类或接口
			if (!editable.isInstance(target)) {
				throw new IllegalArgumentException(StrUtil.format("Target class [{}] not assignable to Editable class [{}]", target.getClass().getName(), editable.getName()));
			}
			actualEditable = editable;
		}
		PropertyDescriptor[] targetPds = null;
		Map<String, PropertyDescriptor> sourcePdMap;
		try {
			sourcePdMap = getFieldNamePropertyDescriptorMap(source.getClass());
			targetPds = getPropertyDescriptors(actualEditable);
		} catch (IntrospectionException e) {
			throw new UtilException(e);
		}
		
		List<String> ignoreList = (ignoreProperties != null ? Arrays.asList(ignoreProperties) : null);

		for (PropertyDescriptor targetPd : targetPds) {
			Method writeMethod = targetPd.getWriteMethod();
			if (writeMethod != null && (ignoreList == null || false == ignoreList.contains(targetPd.getName()))) {
				PropertyDescriptor sourcePd = sourcePdMap.get(targetPd.getName());
				if (sourcePd != null) {
					Method readMethod = sourcePd.getReadMethod();
					// 源对象字段的getter方法返回值必须可转换为目标对象setter方法的第一个参数
					if (readMethod != null && ClassUtil.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
						try {
							Object value = ClassUtil.setAccessible(readMethod).invoke(source);
							ClassUtil.setAccessible(writeMethod).invoke(target, value);
						} catch (Throwable ex) {
							throw new UtilException(ex, "Copy property [{}] to [{}] error: {}", sourcePd.getName(), targetPd.getName(), ex.getMessage());
						}
					}
				}
			}
		}
	}