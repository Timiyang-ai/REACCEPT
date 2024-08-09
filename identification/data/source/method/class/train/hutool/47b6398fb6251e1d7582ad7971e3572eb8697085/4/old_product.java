public static void copyProperties(final Object source, Object target, boolean ignoreCase, CopyOptions copyOptions) {
		if (null == copyOptions) {
			copyOptions = new CopyOptions();
		}
		final boolean ignoreError = copyOptions.ignoreError;

		final Map<String, PropDesc> sourcePdMap = BeanUtil.getBeanDesc(source.getClass()).getPropMap(ignoreCase);
		fillBean(target, new ValueProvider<String>() {
			@Override
			public Object value(String key, Type valueType) {
				final PropDesc sourcePd = sourcePdMap.get(key);
				if (null != sourcePd) {
					final Method getter = sourcePd.getGetter();
					if (null != getter) {
						try {
							return getter.invoke(source);
						} catch (Exception e) {
							if (false == ignoreError) {
								throw new UtilException(e, "Inject [{}] error!", key);
							}
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