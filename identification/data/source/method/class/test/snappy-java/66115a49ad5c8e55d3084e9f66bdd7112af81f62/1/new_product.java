@Override
    public int read(byte[] b, int byteOffset, int byteLength)
            throws IOException
    {
        int writtenBytes = 0;
        for (; writtenBytes < byteLength; ) {

            if (uncompressedCursor >= uncompressedLimit) {
                if (hasNextChunk()) {
                    continue;
                }
                else {
                    return writtenBytes == 0 ? -1 : writtenBytes;
                }
            }
            int bytesToWrite = Math.min(uncompressedLimit - uncompressedCursor, byteLength - writtenBytes);
            System.arraycopy(uncompressed, uncompressedCursor, b, byteOffset + writtenBytes, bytesToWrite);
            writtenBytes += bytesToWrite;
            uncompressedCursor += bytesToWrite;
        }

        return writtenBytes;
    }