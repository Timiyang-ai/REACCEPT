final Capsule setTarget(Path jar) {
        verifyCanCallSetTarget();

        jar = toAbsolutePath(jar);

        if (jar.equals(getJarFile())) // catch simple loops
            throw new RuntimeException("Capsule wrapping loop detected with capsule " + getJarFile());

        if (isFactoryCapsule()) {
            this.jarFile = jar;
            return this;
        }

        final Manifest man;
        boolean isCapsule = false;
        final long start = clock();
        try (JarInputStream jis = openJarInputStream(jar)) {
            man = jis.getManifest();
            if (man == null || man.getMainAttributes().getValue(ATTR_MAIN_CLASS) == null)
                throw new IllegalArgumentException(jar + " is not a capsule or an executable JAR");

            for (JarEntry entry; (entry = jis.getNextJarEntry()) != null;) {
                if (entry.getName().equals(Capsule.class.getName() + ".class")) {
                    isCapsule = true;
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read JAR file " + jar, e);
        }
        time("Read JAR in setTarget", start);

        if (!isCapsule)
            manifest.getMainAttributes().putValue(ATTR_APP_ARTIFACT, jar.toString());
        else {
            log(LOG_VERBOSE, "Wrapping capsule " + jar);
            oc.jarFile = jar;
            insertAfter(loadTargetCapsule(MY_CLASSLOADER, jar));
            oc.dependencyManager = dependencyManager;
        }
        finalizeCapsule(isCapsule);
        return this;
    }