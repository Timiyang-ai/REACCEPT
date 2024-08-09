public void closeEntry() throws IOException {
        // BEGIN android-changed
        if (closed) {
            throw new IOException(Messages.getString("archive.1E")); //$NON-NLS-1$
        }
        // END android-changed
        if (currentEntry == null) {
            return;
        }
        if (currentEntry instanceof java.util.jar.JarEntry) {
            Attributes temp = ((JarEntry) currentEntry).getAttributes();
            if (temp != null && temp.containsKey("hidden")) { //$NON-NLS-1$
                return;
            }
        }
        // Ensure all entry bytes are read
        skip(Long.MAX_VALUE);
        int inB, out;
        if (currentEntry.compressionMethod == DEFLATED) {
            inB = inf.getTotalIn();
            out = inf.getTotalOut();
        } else {
            inB = inRead;
            out = inRead;
        }
        int diff = 0;
        // Pushback any required bytes
        if ((diff = entryIn - inB) != 0) {
            ((PushbackInputStream) in).unread(buf, len - diff, diff);
        }

        if (hasDD) {
            in.read(hdrBuf, 0, EXTHDR);
            if (getLong(hdrBuf, 0) != EXTSIG) {
                throw new ZipException(Messages.getString("archive.1F")); //$NON-NLS-1$
            }
            currentEntry.crc = getLong(hdrBuf, EXTCRC);
            currentEntry.compressedSize = getLong(hdrBuf, EXTSIZ);
            currentEntry.size = getLong(hdrBuf, EXTLEN);
        }
        if (currentEntry.crc != crc.getValue()) {
            throw new ZipException(Messages.getString("archive.20")); //$NON-NLS-1$
        }
        if (currentEntry.compressedSize != inB || currentEntry.size != out) {
            throw new ZipException(Messages.getString("archive.21")); //$NON-NLS-1$
        }

        inf.reset();
        lastRead = inRead = entryIn = len = 0;
        crc.reset();
        currentEntry = null;
    }