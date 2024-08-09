public void merge(IntSet other) {
        if (other instanceof BitIntSet) {
            BitIntSet o = (BitIntSet) other;
            ensureCapacity(Bits.getMax(o.bits) + 1);
            Bits.or(bits, o.bits);
        } else if (other instanceof ListIntSet) {
            ListIntSet o = (ListIntSet) other;
            int sz = o.ints.size();

            if (sz > 0) {
                ensureCapacity(o.ints.get(sz - 1));
            }
            for (int i = 0; i < o.ints.size(); i++) {
                Bits.set(bits, o.ints.get(i), true);
            }
        } else {
            IntIterator iter = other.iterator();
            while (iter.hasNext()) {
                add(iter.next());
            }
        }
    }