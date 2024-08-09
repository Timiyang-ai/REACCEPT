public synchronized void fileCreated(String path) {
        for (FileSystemListener listener : _fileSystemListeners)
            listener.fileCreated(path);
    }