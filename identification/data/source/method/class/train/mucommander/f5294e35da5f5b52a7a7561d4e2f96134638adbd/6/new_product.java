public boolean moveTo(AbstractFile destFile) throws FileTransferException {
        checkCopyPrerequisites(destFile, false);

        // Copy the file and its contents if the file is a directory
        copyRecursively(this, destFile);

        // Note: the above code is the same as #copyTo(), but we don't want to avoid using #copyTo() so that both
        // moveTo() and copyTo() can be overridden separately.

        // Delete the source file and its contents now that it has been copied OK.
        // Note that the file won't be deleted if copyTo() failed (threw an IOException)
        try {
            deleteRecursively();
            return true;
        }
        catch(IOException e) {
            throw new FileTransferException(FileTransferException.DELETING_SOURCE);
        }
    }