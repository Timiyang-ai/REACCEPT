static synchronized SnappyNativeAPI load() {

        if (isInitialized)
            return api;

        isInitialized = true;

        final String nativeLoaderClassName = "org.xerial.snappy.SnappyNativeLoader";
        boolean useNativeCodeInjection = !Boolean.parseBoolean(System.getProperty(KEY_SNAPPY_DISABLE_NATIVE_INJECTION,
                "false"));

        // Use parent class loader to load SnappyNative, since Tomcat, which uses different class loaders for each webapps, cannot load JNI interface twice  
        ClassLoader rootClassLoader = getRootClassLoader();
        try {
            if (!useNativeCodeInjection) {
                // Use the local loader
                Class< ? > loaderClass = LocalSnappyNativeLoader.class;
                loadNativeLibrary(loaderClass);
            }
            else {
                Class< ? > c = Class.forName(nativeLoaderClassName);
                // If this native loader class is already defined, it means that another class loader already loaded the native library of snappy
                api = (SnappyNativeAPI) Class.forName("org.xerial.snappy.SnappyNative").newInstance();
            }
            isLoaded = true;
            return api;
        }
        catch (ClassNotFoundException e) {
            try {

                // Load a byte code 
                byte[] byteCode = getByteCode("/org/xerial/snappy/SnappyNativeLoader.bytecode");
                // In addition, we need to load the other dependent classes (e.g., SnappyNative and SnappyException) using the system class loader
                final String[] classesToPreload = new String[] { "org.xerial.snappy.SnappyNativeAPI",
                        "org.xerial.snappy.SnappyNative", "org.xerial.snappy.SnappyErrorCode" };
                List<byte[]> preloadClassByteCode = new ArrayList<byte[]>(classesToPreload.length);
                for (String each : classesToPreload) {
                    preloadClassByteCode.add(getByteCode(String.format("/%s.class", each.replaceAll("\\.", "/"))));
                }

                // Create SnappyNative class from a byte code
                Class< ? > classLoader = Class.forName("java.lang.ClassLoader");
                Method defineClass = classLoader.getDeclaredMethod("defineClass", new Class[] { String.class,
                        byte[].class, int.class, int.class });

                //ProtectionDomain pd = System.class.getProtectionDomain();

                // ClassLoader.defineClass is a protected method, so we have to make it accessible
                defineClass.setAccessible(true);
                try {
                    // Create a new class using a ClassLoader#defineClass
                    defineClass.invoke(rootClassLoader, nativeLoaderClassName, byteCode, 0, byteCode.length);

                    for (int i = 0; i < classesToPreload.length; ++i) {
                        byte[] b = preloadClassByteCode.get(i);
                        defineClass.invoke(rootClassLoader, classesToPreload[i], b, 0, b.length);
                    }
                }
                finally {
                    // Reset the accessibility to defineClass method
                    defineClass.setAccessible(false);
                }

                // Load the SnappyNativeLoader class
                Class< ? > loaderClass = rootClassLoader.loadClass(nativeLoaderClassName);
                loadNativeLibrary(loaderClass);
                return api;
            }
            catch (ClassNotFoundException ee) {
                throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, ee.getMessage());
            }
            catch (Exception e2) {
                e2.printStackTrace(System.err);
                throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e2.getMessage());
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e.getMessage());
        }

    }