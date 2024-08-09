@Override public Iterable<Element> nonZeroes() {
        return new Iterable<Element>() {
            private int idx = 0;
            private int idxNext = -1;

            /** {@inheritDoc} */
            @NotNull
            @Override public Iterator<Element> iterator() {
                return new Iterator<Element>() {
                    @Override public boolean hasNext() {
                        findNext();

                        return !over();
                    }

                    @Override public Element next() {
                        if (hasNext()) {
                            idx = idxNext;

                            return getElement(idxNext);
                        }

                        throw new NoSuchElementException();
                    }

                    private void findNext() {
                        if (over())
                            return;

                        if (idxNextInitialized() && idx != idxNext)
                            return;

                        if (idxNextInitialized())
                            idx = idxNext + 1;

                        while (idx < size() && isZero(get(idx)))
                            idx++;

                        idxNext = idx++;
                    }

                    private boolean over() {
                        return idxNext >= size();
                    }

                    private boolean idxNextInitialized() {
                        return idxNext != -1;
                    }
                };
            }
        };
    }