public BufferedString toBufferedString() {
        byte[] buf = MemoryManager.malloc1(length() - _skipped.length); // Length of the buffer window minus skipped chars

        int nSkipped = 0;
        for (int i = getOffset(); i < getOffset() + length(); i++) {

            if (Arrays.binarySearch(_skipped, i) >= 0) {
                nSkipped++;
                continue;
            }

            final byte character = getBuffer()[i];
            buf[i - getOffset() - nSkipped] = character;
        }

        return new BufferedString(buf, 0, buf.length);
    }