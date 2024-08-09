public static URL getResource(ClassLoader classLoader,String resourceName){
        Validate.notNull(classLoader, "classLoader can't be null!");
        return classLoader.getResource(resourceName);
    }