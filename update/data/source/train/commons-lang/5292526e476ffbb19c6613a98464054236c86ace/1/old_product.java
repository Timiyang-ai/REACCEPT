public void setUpToClass(final Class<?> clazz) {
        if (clazz != null) {
            Object object = getObject();
            if (object != null && clazz.isInstance(object) == false) {
                throw new IllegalArgumentException("Specified class is not a superclass of the object");
            }
        }
        this.upToClass = clazz;
    }