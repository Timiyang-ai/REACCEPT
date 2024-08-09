public ZipEntry getNextEntry() throws IOException {
        closeEntry();
        if (entriesEnd) {
            return null;
        }

        int x = 0, count = 0;
        while (count != 4) {
            count += x = in.read(hdrBuf, count, 4 - count);
            if (x == -1) {
                return null;
            }
        }
        long hdr = getLong(hdrBuf, 0);
        if (hdr == CENSIG) {
            entriesEnd = true;
            return null;
        }
        if (hdr != LOCSIG) {
            return null;
        }

        // Read the local header
        count = 0;
        while (count != (LOCHDR - LOCVER)) {
            count += x = in.read(hdrBuf, count, (LOCHDR - LOCVER) - count);
            if (x == -1) {
                throw new EOFException();
            }
        }
        int version = getShort(hdrBuf, 0) & 0xff;
        if (version > ZIPLocalHeaderVersionNeeded) {
            throw new ZipException(Messages.getString("archive.22")); //$NON-NLS-1$
        }
        int flags = getShort(hdrBuf, LOCFLG - LOCVER);
        hasDD = ((flags & ZIPDataDescriptorFlag) == ZIPDataDescriptorFlag);
        int cetime = getShort(hdrBuf, LOCTIM - LOCVER);
        int cemodDate = getShort(hdrBuf, LOCTIM - LOCVER + 2);
        int cecompressionMethod = getShort(hdrBuf, LOCHOW - LOCVER);
        long cecrc = 0, cecompressedSize = 0, cesize = -1;
        if (!hasDD) {
            cecrc = getLong(hdrBuf, LOCCRC - LOCVER);
            cecompressedSize = getLong(hdrBuf, LOCSIZ - LOCVER);
            cesize = getLong(hdrBuf, LOCLEN - LOCVER);
        }
        int flen = getShort(hdrBuf, LOCNAM - LOCVER);
        if (flen == 0) {
            throw new ZipException(Messages.getString("archive.23")); //$NON-NLS-1$
        }
        int elen = getShort(hdrBuf, LOCEXT - LOCVER);

        count = 0;
        if (flen > nameBuf.length) {
            nameBuf = new byte[flen];
            charBuf = new char[flen];
        }
        while (count != flen) {
            count += x = in.read(nameBuf, count, flen - count);
            if (x == -1) {
                throw new EOFException();
            }
        }
        currentEntry = createZipEntry(Util.convertUTF8WithBuf(nameBuf, charBuf,
                0, flen));
        currentEntry.time = cetime;
        currentEntry.modDate = cemodDate;
        currentEntry.setMethod(cecompressionMethod);
        if (cesize != -1) {
            currentEntry.setCrc(cecrc);
            currentEntry.setSize(cesize);
            currentEntry.setCompressedSize(cecompressedSize);
        }
        if (elen > 0) {
            count = 0;
            byte[] e = new byte[elen];
            while (count != elen) {
                count += x = in.read(e, count, elen - count);
                if (x == -1) {
                    throw new EOFException();
                }
            }
            currentEntry.setExtra(e);
        }
        // BEGIN android-added
        eof = false;
        // END android-added
        return currentEntry;
    }