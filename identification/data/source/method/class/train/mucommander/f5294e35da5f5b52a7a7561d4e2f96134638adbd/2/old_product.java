public boolean moveTo(AbstractFile destFile) throws FileTransferException {
        // Throw a specific FileTransferException if source and destination files are identical
        if(this.equals(destFile))
            throw new FileTransferException(FileTransferException.SOURCE_AND_DESTINATION_IDENTICAL);

        copyTo(destFile);

        // The file won't be deleted if copyTo() failed (threw an IOException)
        try {
            delete();
            return true;
        }
        catch(IOException e) {
            throw new FileTransferException(FileTransferException.DELETING_SOURCE);
        }
    }