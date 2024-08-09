@Override
    public int read(byte[] buffer, int offset, int length) throws IOException {
        if (mEntry != null) {
            return -1;
        }
        int r = super.read(buffer, offset, length);
        if (verStream != null && !eos) {
            if (r == -1) {
                eos = true;
                if (verifier != null) {
                    if (isMeta) {
                        verifier.addMetaEntry(jarEntry.getName(),
                                ((ByteArrayOutputStream) verStream)
                                        .toByteArray());
                        try {
                            verifier.readCertificates();
                        } catch (SecurityException e) {
                            verifier = null;
                            throw e;
                        }
                    } else {
                        ((JarVerifier.VerifierEntry) verStream).verify();
                    }
                }
            } else {
                verStream.write(buffer, offset, r);
            }
        }
        return r;
    }