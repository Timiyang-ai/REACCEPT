public static void updateLoader(URL[] urls) {
        DynamicClassLoader loader = (DynamicClassLoader) Thread.currentThread().getContextClassLoader();
        for (int i = 0; i < urls.length; i++) {
            loader.addURL(urls[i]);
        }
    }