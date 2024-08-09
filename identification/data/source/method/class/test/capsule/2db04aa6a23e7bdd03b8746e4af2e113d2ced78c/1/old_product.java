static Capsule newCapsule(Path jarFile, Path cacheDir) {
        try {
            final String mainClassName = getMainClass(jarFile);
            if (mainClassName != null) {
                final Class<?> clazz = Capsule.class.getClassLoader().loadClass(mainClassName);
                if (isCapsuleClass(clazz)) {
                    final Constructor<?> ctor = clazz.getDeclaredConstructor(Path.class, Path.class);
                    ctor.setAccessible(true);
                    return (Capsule) ctor.newInstance(jarFile, cacheDir);
                }
            }
            throw new RuntimeException(jarFile + " does not appear to be a valid capsule.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(jarFile + " does not appear to be a valid capsule.", e);
        } catch (InvocationTargetException e) {
            throw rethrow(e.getTargetException());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not instantiate capsule.", e);
        }
    }