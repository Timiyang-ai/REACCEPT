static Capsule newCapsule(ClassLoader cl, Path jarFile, Path cacheDir) {
        try {
            final String mainClassName = getMainClass(jarFile);
            if (mainClassName != null) {
                final Class<?> clazz = cl.loadClass(mainClassName);
                if (isCapsuleClass(clazz))
                    return (Capsule) accessible(clazz.getDeclaredConstructor(Path.class, Path.class)).newInstance(jarFile, cacheDir);
            }
            throw new RuntimeException(jarFile + " does not appear to be a valid capsule.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(jarFile + " does not appear to be a valid capsule.", e);
        } catch (IncompatibleClassChangeError e) {
            throw new RuntimeException("Caplet " + jarFile + " is not compatible with this capsule (" + VERSION + ")");
        } catch (InvocationTargetException e) {
            throw rethrow(e.getTargetException());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not instantiate capsule.", e);
        }
    }