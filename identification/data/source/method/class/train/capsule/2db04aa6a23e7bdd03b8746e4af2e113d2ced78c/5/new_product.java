private Capsule newCapsule(Path jarFile, Capsule pred) {
        try {
            return accessible(loadCapsule(newClassLoader(MY_CLASSLOADER, jarFile), jarFile).getDeclaredConstructor(Capsule.class)).newInstance(pred);
        } catch (IncompatibleClassChangeError e) {
            throw new RuntimeException("Caplet " + jarFile + " is not compatible with this capsule (" + VERSION + ")");
        } catch (InvocationTargetException e) {
            throw rethrow(e.getTargetException());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Could not instantiate capsule.", e);
        }
    }