public boolean deleteRecursively() {
        if (isDirectory()) {
            for (UnixPath path : listContentsOfDirectory()) {
                path.deleteRecursively();
            }
        }

        return deleteIfExists();
    }