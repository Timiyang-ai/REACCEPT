public File cacheFile (File fileToCache, String digest) throws IOException
    {
        File cacheLocation = new File(_cacheDir, digest.substring(0, 2));
        createDirectoryIfNecessary(cacheLocation);

        File cachedFile = new File(cacheLocation, digest + getFileSuffix(fileToCache));
        File lastAccessedFile = new File(
                cacheLocation, cachedFile.getName() + LAST_ACCESSED_FILE_SUFFIX);

        if (!cachedFile.exists()) {
            createNewFile(cachedFile);
            FileUtil.copy(fileToCache, cachedFile);
        }

        if (lastAccessedFile.exists()) {
            lastAccessedFile.setLastModified(System.currentTimeMillis());
        } else {
            createNewFile(lastAccessedFile);
        }

        return cachedFile;
    }