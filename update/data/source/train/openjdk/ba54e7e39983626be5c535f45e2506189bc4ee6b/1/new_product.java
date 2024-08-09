public Class<?> defineClass(byte[] bytes) throws IllegalAccessException {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null)
                sm.checkPermission(new RuntimePermission("defineClass"));
            if ((lookupModes() & PACKAGE) == 0)
                throw new IllegalAccessException("Lookup does not have PACKAGE access");
            assert (lookupModes() & (MODULE|PUBLIC)) != 0;

            // parse class bytes to get class name (in internal form)
            bytes = bytes.clone();
            String name;
            try {
                ClassReader reader = new ClassReader(bytes);
                name = reader.getClassName();
            } catch (RuntimeException e) {
                // ASM exceptions are poorly specified
                ClassFormatError cfe = new ClassFormatError();
                cfe.initCause(e);
                throw cfe;
            }

            // get package and class name in binary form
            String cn, pn;
            int index = name.lastIndexOf('/');
            if (index == -1) {
                cn = name;
                pn = "";
            } else {
                cn = name.replace('/', '.');
                pn = cn.substring(0, index);
            }
            if (!pn.equals(lookupClass.getPackageName())) {
                throw new IllegalArgumentException("Class not in same package as lookup class");
            }

            // invoke the class loader's defineClass method
            ClassLoader loader = lookupClass.getClassLoader();
            ProtectionDomain pd = (loader != null) ? lookupClassProtectionDomain() : null;
            String source = "__Lookup_defineClass__";
            Class<?> clazz = SharedSecrets.getJavaLangAccess().defineClass(loader, cn, bytes, pd, source);
            assert clazz.getClassLoader() == lookupClass.getClassLoader()
                    && clazz.getPackageName().equals(lookupClass.getPackageName())
                    && protectionDomain(clazz) == lookupClassProtectionDomain();
            return clazz;
        }