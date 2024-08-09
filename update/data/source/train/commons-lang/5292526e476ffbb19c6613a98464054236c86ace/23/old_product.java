public StrBuilder reverse() {
        if (size == 0) {
            return this;
        }
        
        int half = size / 2;
        char[] buf = buffer;
        for (int leftIdx = 0, rightIdx = size - 1; leftIdx < half; leftIdx++,rightIdx--) {
            char swap = buf[leftIdx];
            buf[leftIdx] = buf[rightIdx];
            buf[rightIdx] = swap;
        }
        return this;
    }