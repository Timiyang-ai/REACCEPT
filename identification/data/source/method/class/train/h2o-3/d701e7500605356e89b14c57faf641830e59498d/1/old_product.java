public BufferedString toBufferedString() {
        if (_skipped.length == 0)
            return _bufferedString;

        byte[] buf = MemoryManager.malloc1(_bufferedString._len - _skipped.length); // Length of the buffer window minus skipped chars

        int copyStart = _bufferedString._off;
        int nSkipped = 0;
        for (int skippedIndex : _skipped) {
            for (int i = copyStart; i < skippedIndex; i++) {
                buf[i - _bufferedString._off - nSkipped] = _bufferedString._buf[i];
            }
            nSkipped++;

            copyStart = skippedIndex;
        }

        int windowEnd = _bufferedString._off + _bufferedString._len - 1;
        for (int i = copyStart; i < windowEnd; i++) {
            buf[i - _bufferedString._off - _skipped.length] = _bufferedString._buf[i];
        }

        return new BufferedString(buf, 0, buf.length);
    }