@Override
    public Object clone() {
        try {
            BitSet clone = (BitSet) super.clone();
            clone.bits = bits.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }