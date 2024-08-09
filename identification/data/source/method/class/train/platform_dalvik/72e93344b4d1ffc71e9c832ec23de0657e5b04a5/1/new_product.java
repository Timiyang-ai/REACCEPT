@Override
    public IntIterator iterator() {
        return new IntIterator() {
            private int idx = 0;

            /** {@inheritDoc} */
            @Override
            public boolean hasNext() {
                return idx < ints.size();
            }

            /** {@inheritDoc} */
            @Override
            public int next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return ints.get(idx++);
            }
        };
    }