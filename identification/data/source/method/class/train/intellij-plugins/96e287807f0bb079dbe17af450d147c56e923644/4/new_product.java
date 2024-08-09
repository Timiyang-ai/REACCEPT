@Override
    public synchronized void fileDeleted(String path) {
        for (FileSystemListener listener : _fileSystemListeners)
            listener.fileDeleted(path);
    }