public String getCanonicalPath() throws IOException {
        return realpath(getAbsolutePath());
    }