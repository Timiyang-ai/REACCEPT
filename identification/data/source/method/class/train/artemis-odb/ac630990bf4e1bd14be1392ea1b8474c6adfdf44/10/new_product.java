<T extends EntityFactory<T>> T createFactory(Class<T> factory) {
		if (!factory.isInterface())
			throw new RuntimeException("Expected interface for type: " + factory);
		
		assertInitialized();

		String impl = factory.getCanonicalName() + "Impl";
		try {
			Class<?> implClass = ClassReflection.forName(impl);
			Constructor constructor = ClassReflection.getConstructor(implClass, World.class);
			@SuppressWarnings("unchecked")
			T instance = (T) constructor.newInstance(this);
			return instance;
		} catch (ReflectionException e) {
			throw new RuntimeException(e);
		}
	}