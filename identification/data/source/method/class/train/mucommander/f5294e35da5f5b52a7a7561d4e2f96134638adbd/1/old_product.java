public final void moveTo(AbstractFile destFile) throws IOException {
        // First, try to rename the file if the operation is supported
        if(isFileOperationSupported(FileOperation.RENAME)) {
            try {
                renameTo(destFile);
                // Rename was a success, all done.
                return;
            }
            catch(IOException e) {
                // Fail silently
            }
        }

        // Fall back to moving the file manually

        copyTo(destFile);

        // Delete the source file and its contents now that it has been copied OK.
        // Note that the file won't be deleted if copyTo() failed (threw an IOException)
        try {
            deleteRecursively();
        }
        catch(IOException e) {
            throw new FileTransferException(FileTransferException.DELETING_SOURCE);
        }
    }