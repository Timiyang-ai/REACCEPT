static synchronized SnappyNativeAPI load() {

        if (api != null)
            return api;

        final String nativeLoaderClassName = "org.xerial.snappy.SnappyNativeLoader";
        boolean useNativeCodeInjection = !Boolean.parseBoolean(System.getProperty(KEY_SNAPPY_DISABLE_NATIVE_INJECTION,
                "false"));

        if (useNativeCodeInjection) {
            try {
                Class< ? > c = Class.forName(nativeLoaderClassName);
                // If this native loader class is already defined, it means that another class loader already loaded the native library of snappy
                api = (SnappyNativeAPI) Class.forName("org.xerial.snappy.SnappyNative").newInstance();
                isLoaded = true;
                return api;
            }
            catch (ClassNotFoundException e) {
                // do loading
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e.getMessage());
            }
        }

        // Prepare SnappyNativeLoader or LocalSnappyNativeLoader
        Class< ? > nativeLoader = prepareNativeLoader();
        // Load the code
        loadNativeLibrary(nativeLoader);
        return api;
    }