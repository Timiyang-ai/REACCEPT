public boolean copyTo(AbstractFile destFile) throws FileTransferException {
        checkCopyPrerequisites(destFile, false);

        // Copy the file and its contents if the file is a directory
        copyRecursively(this, destFile);

        return true;
    }