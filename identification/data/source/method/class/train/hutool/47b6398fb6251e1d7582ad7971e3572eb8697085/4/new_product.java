public static void copyProperties(final Object source, Object target, boolean ignoreCase, CopyOptions copyOptions) {
		if (null == copyOptions) {
			copyOptions = new CopyOptions();
		}
		BeanCopier.create(source, target, copyOptions).copy();
	}