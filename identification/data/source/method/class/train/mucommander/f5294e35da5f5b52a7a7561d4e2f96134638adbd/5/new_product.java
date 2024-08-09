public final void copyTo(AbstractFile destFile) throws IOException {
        // First, try to perform a remote copy of the file if the operation is supported
        if(isFileOperationSupported(FileOperation.COPY_REMOTELY)) {
            try {
                copyRemotelyTo(destFile);
                // Operation was a success, all done.
                return;
            }
            catch(IOException e) {
                // Fail silently
            }
        }

        // Fall back to copying the file manually

        checkCopyPrerequisites(destFile, false);

        // Copy the file and its contents if the file is a directory
        copyRecursively(this, destFile);
    }