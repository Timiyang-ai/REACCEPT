public static URL getRootClassPath(){
        return getClassPath(getClassLoaderByClass(ClassLoaderUtil.class));
    }