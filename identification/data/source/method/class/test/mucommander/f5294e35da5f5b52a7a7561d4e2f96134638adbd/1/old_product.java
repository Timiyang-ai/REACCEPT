public boolean copyTo(AbstractFile destFile) throws FileTransferException {
        // Throw a specific FileTransferException if source and destination files are identical
        if(this.equals(destFile))
            throw new FileTransferException(FileTransferException.SOURCE_AND_DESTINATION_IDENTICAL);
        
        InputStream in;

        try {
            in = getInputStream();
        }
        catch(IOException e) {
            throw new FileTransferException(FileTransferException.OPENING_SOURCE);
        }

        try {
            destFile.copyStream(in, false);
            return true;
        }
        finally {
            // Close stream even if copyStream() threw an IOException
            try {
                in.close();
            }
            catch(IOException e) {
                throw new FileTransferException(FileTransferException.CLOSING_SOURCE);
            }
        }
    }