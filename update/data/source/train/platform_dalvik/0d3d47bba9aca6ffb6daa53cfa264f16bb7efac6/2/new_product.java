public Enumeration<? extends ZipEntry> entries() {
        checkNotClosed();

        return new Enumeration<ZipEntry>() {
            private int i = 0;

            public boolean hasMoreElements() {
                checkNotClosed();
                return i < mEntryList.size();
            }

            public ZipEntry nextElement() {
                checkNotClosed();
                if (i >= mEntryList.size())
                    throw new NoSuchElementException();
                return (ZipEntry) mEntryList.get(i++);
            }
        };
    }