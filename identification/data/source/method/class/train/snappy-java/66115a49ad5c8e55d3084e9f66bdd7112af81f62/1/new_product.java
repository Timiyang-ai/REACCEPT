@Override
    public int read()
            throws IOException
    {
        if (uncompressedCursor < uncompressedLimit) {
            return uncompressed[uncompressedCursor++] & 0xFF;
        }
        else {
            if (hasNextChunk()) {
                return read();
            }
            else {
                return -1;
            }
        }
    }