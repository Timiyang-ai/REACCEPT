static synchronized Object load() {

        if (api != null)
            return api;

        try {
            if (!hasInjectedNativeLoader()) {
                // Inject SnappyNativeLoader (src/main/resources/org/xerial/snappy/SnappyNativeLoader.bytecode) to the root class loader  
                Class< ? > nativeLoader = injectSnappyNativeLoader();
                // Load the JNI code using the injected loader
                loadNativeLibrary(nativeLoader);
            }

            isLoaded = true;
            // Look up SnappyNative, injected to the root classloder, using reflection in order to avoid the initialization of SnappyNative class in this context class loader.
            Object nativeCode = Class.forName("org.xerial.snappy.SnappyNative").newInstance();
            api = nativeCode;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e.getMessage());
        }

        return api;
    }