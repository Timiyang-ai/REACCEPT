private static String getMainClass(Path jar) throws IOException {
        try (final JarInputStream jis = new JarInputStream(Files.newInputStream(jar))) {
            return getMainClass(jis.getManifest());
        }
    }