public BufferedString toBufferedString() {
        byte[] buf = MemoryManager.malloc1(_bufferedString.length() - _skipped.length); // Length of the buffer window minus skipped chars

        int nSkipped = 0;
        for (int i = _bufferedString.getOffset(); i < _bufferedString.getOffset() + _bufferedString.length(); i++) {

            if (Arrays.binarySearch(_skipped, i) >= 0) {
                nSkipped++;
                continue;
            }

            final byte character = getBuffer()[i];
            buf[i - _bufferedString.getOffset() - nSkipped] = character;
        }

        return new BufferedString(buf, 0, buf.length);
    }