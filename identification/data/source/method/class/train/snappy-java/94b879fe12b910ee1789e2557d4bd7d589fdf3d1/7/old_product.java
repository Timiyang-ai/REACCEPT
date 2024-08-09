static synchronized SnappyNativeAPI load() {

        if (isInitialized)
            return api;

        isInitialized = true;
        final String nativeLoaderClassName = "org.xerial.snappy.SnappyNativeLoader";
        final String[] classesToPreload = new String[] { "org.xerial.snappy.SnappyNativeAPI",
                "org.xerial.snappy.SnappyNative", "org.xerial.snappy.SnappyErrorCode" };

        try {
            Class< ? > c = Class.forName(nativeLoaderClassName);
            // If this native loader class is already defined, it means that another class loader already loaded the native library of snappy
            api = (SnappyNativeAPI) Class.forName("org.xerial.snappy.SnappyNative").newInstance();
            isLoaded = true;
            return api;
        }
        catch (ClassNotFoundException e) {
            try {
                // Load a byte code 
                byte[] byteCode = getByteCode("/org/xerial/snappy/SnappyNativeLoader.bytecode");
                // In addition, we need to load the other dependent classes (e.g., SnappyNative and SnappyException) using the system class loader
                List<byte[]> preloadClassByteCode = new ArrayList<byte[]>(classesToPreload.length);
                for (String each : classesToPreload) {
                    preloadClassByteCode.add(getByteCode(String.format("/%s.class", each.replaceAll("\\.", "/"))));
                }

                // Create SnappyNative class from a byte code
                Class< ? > classLoader = Class.forName("java.lang.ClassLoader");
                Method defineClass = classLoader.getDeclaredMethod("defineClass", new Class[] { String.class,
                        byte[].class, int.class, int.class, ProtectionDomain.class });

                // Use parent class loader to load SnappyNative, since Tomcat, which uses different class loaders for each webapps, cannot load JNI interface twice  
                ClassLoader systemClassLoader = getRootClassLoader();
                ProtectionDomain pd = System.class.getProtectionDomain();

                // ClassLoader.defineClass is a protected method, so we have to make it accessible
                defineClass.setAccessible(true);
                try {
                    // Create a new class using a ClassLoader#defineClass
                    defineClass.invoke(systemClassLoader, nativeLoaderClassName, byteCode, 0, byteCode.length, pd);

                    for (int i = 0; i < classesToPreload.length; ++i) {
                        byte[] b = preloadClassByteCode.get(i);
                        defineClass.invoke(systemClassLoader, classesToPreload[i], b, 0, b.length, pd);
                    }
                }
                finally {
                    // Reset the accessibility to defineClass method
                    defineClass.setAccessible(false);
                }

                // Load the snappy loader class
                Class< ? > loaderClass = systemClassLoader.loadClass(nativeLoaderClassName);
                if (loaderClass != null) {

                    final String KEY_SNAPPY_NATIVE_LOAD_FLAG = "org.xerial.snappy.native_is_loaded";
                    boolean isNativeLibLoaded = Boolean.parseBoolean(System.getProperty(KEY_SNAPPY_NATIVE_LOAD_FLAG,
                            "false"));
                    if (!isNativeLibLoaded) {
                        File nativeLib = findNativeLibrary();
                        if (nativeLib != null) {
                            // Load extracted or specified snappyjava native library. 
                            Method loadMethod = loaderClass.getDeclaredMethod("load", new Class[] { String.class });
                            loadMethod.invoke(null, nativeLib.getAbsolutePath());
                        }
                        else {
                            // Load preinstalled snappyjava (in the path -Djava.library.path) 
                            Method loadMethod = loaderClass.getDeclaredMethod("loadLibrary",
                                    new Class[] { String.class });
                            loadMethod.invoke(null, "snappyjava");
                        }

                        System.setProperty(KEY_SNAPPY_NATIVE_LOAD_FLAG, "true");
                    }

                    // And also, preload the other dependent classes 
                    for (String each : classesToPreload) {
                        systemClassLoader.loadClass(each);
                    }
                    isLoaded = true;

                    api = (SnappyNativeAPI) Class.forName("org.xerial.snappy.SnappyNative").newInstance();
                    return api;
                }
            }
            catch (ClassNotFoundException ee) {
                throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, ee.getMessage());
            }
            catch (Exception e2) {
                e2.printStackTrace();
                throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e2.getMessage());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e.getMessage());
        }

        throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY);
    }