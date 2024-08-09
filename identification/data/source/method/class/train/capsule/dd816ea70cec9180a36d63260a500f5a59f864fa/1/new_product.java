private static String getMainClass(Path jar) throws IOException {
        return getMainClass(getManifest(jar));
    }