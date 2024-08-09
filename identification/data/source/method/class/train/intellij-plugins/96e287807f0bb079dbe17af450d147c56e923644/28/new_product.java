@Override
    public synchronized void classDeleted(String classFqn) {
        for (FileSystemListener listener : _fileSystemListeners)
            listener.classDeleted(classFqn);
    }