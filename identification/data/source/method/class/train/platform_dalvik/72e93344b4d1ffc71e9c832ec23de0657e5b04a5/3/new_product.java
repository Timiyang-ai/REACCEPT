@Override
    public IntIterator iterator() {
        return new IntIterator() {
            private int idx = Bits.findFirst(bits, 0);

            /** {@inheritDoc} */
            @Override
            public boolean hasNext() {
                return idx >= 0;
            }

            /** {@inheritDoc} */
            @Override
            public int next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                int ret = idx;

                idx = Bits.findFirst(bits, idx+1);

                return ret;
            }
        };
    }