public synchronized void classCreated(String classFqn) {
        for (FileSystemListener listener : _fileSystemListeners)
            listener.classCreated(classFqn);
    }