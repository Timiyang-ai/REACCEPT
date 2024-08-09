final Capsule setTarget(Path jar) {
        verifyCanCallSetTarget();

        jar = toAbsolutePath(jar);

        if (jar.equals(jarFile)) // catch simple loops
            throw new RuntimeException("Capsule wrapping loop detected with capsule " + jarFile);

        final Manifest man;
        boolean isCapsule = false;
        final long start = clock();
        try (JarInputStream jis = openJarInputStream(jar)) {
            man = jis.getManifest();
            if (man == null || man.getMainAttributes().getValue(ATTR_MAIN_CLASS) == null)
                throw new IllegalArgumentException(jar + " is not a capsule or an executable JAR");

            for (JarEntry entry; (entry = jis.getNextJarEntry()) != null;) {
                if (entry.getName().equals(Capsule.class.getName() + ".class"))
                    isCapsule = true;
                else if (entry.getName().equals(POM_FILE))
                    this.pom = createPomReader0(jis);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read JAR file " + jar, e);
        }
        time("Read JAR in setTarget", start);

        if (!isCapsule)
            manifest.getMainAttributes().putValue(ATTR_APP_ARTIFACT, jar.toString());
        else {
            log(LOG_VERBOSE, "Wrapping capsule " + jar);

            this.jarFile = jar;
            merge(manifest, man);
            if (dependencyManager != null)
                setDependencyRepositories(getRepositories());
        }
        finalizeCapsule(isCapsule);
        return this;
    }