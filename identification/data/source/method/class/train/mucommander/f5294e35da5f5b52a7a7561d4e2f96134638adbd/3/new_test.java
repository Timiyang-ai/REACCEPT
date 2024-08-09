    protected void copyTo(AbstractFile sourceFile, AbstractFile destFile, boolean useRemoteCopy) throws IOException {
        if(useRemoteCopy)
            sourceFile.copyRemotelyTo(destFile);
        else
            sourceFile.copyTo(destFile);
    }