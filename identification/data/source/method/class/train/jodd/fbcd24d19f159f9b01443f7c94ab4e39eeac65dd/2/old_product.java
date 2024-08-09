public static String[] resolveParameterNames(AccessibleObject methodOrCtor) {
		Class[] paramTypes;
		Class declaringClass;
		String name;
		if (methodOrCtor instanceof Method) {
			Method method = (Method) methodOrCtor;
			paramTypes = method.getParameterTypes();
			name = method.getName();
			declaringClass = method.getDeclaringClass();
		} else {
			Constructor constructor = (Constructor) methodOrCtor;
			paramTypes = constructor.getParameterTypes();
			declaringClass = constructor.getDeclaringClass();
			name = CTOR_METHOD;
		}

		if (paramTypes.length == 0) {
			return MethodFinder.EMPTY_NAMES;
		}

		InputStream stream;
		try {
			stream = ClassLoaderUtil.getClassAsStream(declaringClass);
		} catch (IOException ioex) {
			throw new ParamoException("Unable to read class bytes.", ioex);
		}
		try {
			ClassReader reader = new ClassReader(stream);
			MethodFinder visitor = new MethodFinder(name, paramTypes);
			reader.accept(visitor, 0);
			return visitor.getParameterNames();
		} catch (IOException ioex) {
			throw new ParamoException(ioex);
		}
	}