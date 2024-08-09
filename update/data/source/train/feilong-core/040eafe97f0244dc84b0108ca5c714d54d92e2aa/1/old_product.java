public static URL getClassPath(){
        ClassLoader classLoader = getClassLoaderByClass(ClassLoaderUtil.class);
        return getClassPath(classLoader);
    }