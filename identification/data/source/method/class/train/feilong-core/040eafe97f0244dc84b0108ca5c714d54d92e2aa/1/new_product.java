public static URL getResource(String resourceName){
        return getResource(getClassLoaderByClass(ClassLoaderUtil.class), resourceName);
    }