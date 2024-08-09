private static Capsule newCapsule(Path jarFile, Path cacheDir) {
        try {
            final Class<?> clazz = Class.forName(CUSTOM_CAPSULE_CLASS_NAME);
            try {
                Constructor<?> ctor = clazz.getDeclaredConstructor(Path.class, Path.class);
                ctor.setAccessible(true);
                return (Capsule) ctor.newInstance(jarFile, cacheDir);
            } catch (Exception e) {
                throw new RuntimeException("Could not launch custom capsule.", e);
            }
        } catch (ClassNotFoundException e) {
            return new Capsule(jarFile, cacheDir);
        }
    }