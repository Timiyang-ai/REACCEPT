public BufferedString toBufferedString() {
        if (_skipped.length == 0)
            return _bufferedString;

        byte[] buf = MemoryManager.malloc1(_bufferedString._len - _skipped.length); // Length of the buffer window minus skipped chars

        int copyStart = _bufferedString._off;
        int target = 0;
        for (int skippedIndex : _skipped) {
            for (int i = copyStart; i < skippedIndex; i++) {
                buf[target++] = _bufferedString._buf[i];
            }
            copyStart = skippedIndex + 1;
        }

        int windowEnd = _bufferedString._off + _bufferedString._len;
        for (int i = copyStart; i < windowEnd; i++) {
            buf[target++] = _bufferedString._buf[i];
        }
        assert target == buf.length;
        return new BufferedString(buf, 0, buf.length);
    }