public FileRecords read(int position, int size) throws IOException {
        if (position < 0)
            throw new IllegalArgumentException("Invalid position: " + position);
        if (size < 0)
            throw new IllegalArgumentException("Invalid size: " + size);

        int end = this.start + position + size;
        // handle integer overflow or if end is beyond the end of the file
        if (end < 0 || end >= start + sizeInBytes())
            end = start + sizeInBytes();
        return new FileRecords(file, channel, this.start + position, end, true);
    }