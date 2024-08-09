public Manifest getManifest() throws java.io.IOException {
        return (Manifest)getJarFile().getManifest().clone();
    }