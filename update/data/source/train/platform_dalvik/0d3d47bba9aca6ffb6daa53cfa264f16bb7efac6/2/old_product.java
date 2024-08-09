public Enumeration<? extends ZipEntry> entries() {
        return new Enumeration<ZipEntry>() {
            private int i = 0;

            public boolean hasMoreElements() {
                if (mRaf == null) throw new IllegalStateException("Zip File closed.");
                return i < mEntryList.size();
            }

            public ZipEntry nextElement() {
                if (mRaf == null) throw new IllegalStateException("Zip File closed.");
                if (i >= mEntryList.size())
                    throw new NoSuchElementException();
                return (ZipEntry) mEntryList.get(i++);
            }
        };
    }