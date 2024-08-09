@Override public Iterable<Element> all() {
        return new Iterable<Element>() {
            private int idx;

            /** {@inheritDoc} */
            @NotNull
            @Override public Iterator<Element> iterator() {
                return new Iterator<Element>() {
                    /** {@inheritDoc} */
                    @Override public boolean hasNext() {
                        return size() > 0 && idx < size();
                    }

                    /** {@inheritDoc} */
                    @Override public Element next() {
                        if (hasNext())
                            return getElement(idx++);

                        throw new NoSuchElementException();
                    }
                };
            }
        };
    }