default InputStream toInputStream() {
        return new FastByteArrayInputStream(array());
    }