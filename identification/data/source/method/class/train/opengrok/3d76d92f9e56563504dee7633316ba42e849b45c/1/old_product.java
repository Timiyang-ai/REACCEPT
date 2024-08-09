public String getPathRelativeToSourceRoot(File file, int stripCount) throws IOException {
        String sourceRoot = getSourceRootPath();
        if (sourceRoot == null) {
            throw new FileNotFoundException("Source Root Not Found");
        }

        String maybeRelPath = PathUtils.getRelativeToCanonical(file.getPath(),
            sourceRoot, getAllowedSymlinks());
        File maybeRelFile = new File(maybeRelPath);
        if (!maybeRelFile.isAbsolute()) {
            // N.b. OpenGrok has a weird convention that
            // source-root "relative" paths must start with a '/' as they are
            // elsewhere directly appended to env.getSourceRootPath() and also
            // stored as such.
            maybeRelPath = File.separator + maybeRelPath;
            return maybeRelPath.substring(stripCount);
        }

        throw new FileNotFoundException("Failed to resolve [" + file.getPath()
                + "] relative to source root [" + sourceRoot + "]");
    }