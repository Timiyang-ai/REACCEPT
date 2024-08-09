static synchronized Object load()
    {
        if (api != null)
            return api;

        try {
            loadNativeLibrary();

            isLoaded = true;
            // Look up SnappyNative, injected to the root classloder, using reflection in order to avoid the initialization of SnappyNative class in this context class loader.
            Object nativeCode = Class.forName("org.xerial.snappy.SnappyNative").newInstance();
            setApi(nativeCode);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e.getMessage());
        }

        return api;
    }