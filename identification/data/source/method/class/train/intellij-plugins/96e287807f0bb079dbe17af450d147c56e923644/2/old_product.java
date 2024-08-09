public synchronized void fileContentsChanged(IResource changedFile) {
        for (FileSystemListener listener : _fileSystemListeners)
            listener.fileContentsChanged(changedFile);
    }