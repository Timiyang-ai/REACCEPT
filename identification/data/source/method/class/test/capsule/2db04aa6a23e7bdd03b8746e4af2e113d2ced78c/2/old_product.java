static Capsule newCapsule(ClassLoader cl, Path jarFile) {
        try {
            return accessible(loadCapsule(cl, jarFile).getDeclaredConstructor(Path.class)).newInstance(jarFile);
        } catch (IncompatibleClassChangeError e) {
            throw new RuntimeException("Caplet " + jarFile + " is not compatible with this capsule (" + VERSION + ")");
        } catch (InvocationTargetException e) {
            throw rethrow(e.getTargetException());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not instantiate capsule.", e);
        }
    }