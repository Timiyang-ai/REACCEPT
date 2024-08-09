@Deprecated
    public void replaceExtension(String name, Class<?> clazz) {
        getExtensionClasses(); // load classes

        if(!cachedClasses.get().containsKey(name)) {
            throw new IllegalStateException("Extension name " +
                    name + " not existed(Extension " + type + ")!");
        }
        if(!type.isAssignableFrom(clazz)) {
            throw new IllegalStateException("Input type " +
                    clazz + "not implement Extension " + type);
        }
        if(clazz.isInterface()) {
            throw new IllegalStateException("Input type " +
                    clazz + "can not be interface!");
        }

        cachedNames.put(clazz, name);
        cachedClasses.get().put(name, clazz);
        cachedInstances.remove(name);
    }