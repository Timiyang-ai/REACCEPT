public static URL getResource(String resourceName){
        ClassLoader classLoader = getClassLoaderByClass(ClassLoaderUtil.class);
        return getResource(classLoader, resourceName);
    }