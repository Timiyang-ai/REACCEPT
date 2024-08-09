public static void updateLoader(URL[] urls) {
        ClassLoader l = Thread.currentThread().getContextClassLoader();
        if (l instanceof DynamicClassLoader) {
            DynamicClassLoader loader = (DynamicClassLoader) l;
            for (int i = 0; i < urls.length; i++) {
                loader.addURL(urls[i]);
            }
        }
    }