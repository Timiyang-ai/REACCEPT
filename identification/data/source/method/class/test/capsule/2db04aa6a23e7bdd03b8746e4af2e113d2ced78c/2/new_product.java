static Capsule newCapsule(ClassLoader cl, Path jarFile) {
        try {
            final ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            try {
                Thread.currentThread().setContextClassLoader(cl);
                return accessible(loadCapsule(cl, jarFile).getDeclaredConstructor(Path.class)).newInstance(jarFile);
            } finally {
                Thread.currentThread().setContextClassLoader(ccl);
            }
        } catch (IncompatibleClassChangeError e) {
            throw new RuntimeException("Caplet " + jarFile + " is not compatible with this capsule (" + VERSION + ")");
        } catch (InvocationTargetException e) {
            throw rethrow(e.getTargetException());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not instantiate capsule.", e);
        }
    }