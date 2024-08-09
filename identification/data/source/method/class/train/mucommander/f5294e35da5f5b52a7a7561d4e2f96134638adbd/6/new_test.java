    protected void moveTo(AbstractFile sourceFile, AbstractFile destFile, boolean useRenameTo) throws IOException {
        if(useRenameTo)
            sourceFile.renameTo(destFile);
        else
            sourceFile.moveTo(destFile);
    }