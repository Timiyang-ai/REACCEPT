public void remove(int value) {
        if (value < Bits.getMax(bits)) {
            Bits.set(bits, value, false);
        }
    }