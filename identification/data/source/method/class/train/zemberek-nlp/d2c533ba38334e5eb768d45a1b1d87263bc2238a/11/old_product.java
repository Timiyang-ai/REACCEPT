public void fill(boolean bitValue) {
        if (bitValue) {
            Arrays.fill(words, 0xffffffffffffffffL);
            int last = (int) (size / 64);
            words[last] &= cutMasks[(int) (size & mod64Mask)] >>> 1;
        } else
            Arrays.fill(words, 0);
    }