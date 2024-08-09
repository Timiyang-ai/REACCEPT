static SnappyNativeAPI load() {

        if (api != null)
            return api;

        boolean useNativeCodeInjection = !Boolean.parseBoolean(System.getProperty(KEY_SNAPPY_DISABLE_NATIVE_INJECTION,
                "false"));
        //System.out.println("use native code injection: " + useNativeCodeInjection);

        try {
            if (useNativeCodeInjection) {
                if (!hasInjectedNativeLoader()) {
                    // Prepare SnappyNativeLoader or LocalSnappyNativeLoader
                    Class< ? > nativeLoader = injectSnappyNativeLoader();
                    // Load the JNI code
                    loadNativeLibrary(nativeLoader);
                }
            }
            else {
                if (!isLoaded) {
                    // load locally
                    File nativeLib = findNativeLibrary();
                    if (nativeLib != null) {
                        System.load(nativeLib.getAbsolutePath());
                    }
                    else {
                        // Load pre-installed libsnappyjava (in the path -Djava.library.path)
                        System.loadLibrary("snappyjava");
                    }
                }
            }

            isLoaded = true;
            api = (SnappyNativeAPI) Class.forName("org.xerial.snappy.SnappyNative").newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e.getMessage());
        }

        return api;
    }