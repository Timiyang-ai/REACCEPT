public FileRecords read(int position, int size) throws IOException {
        if (position < 0)
            throw new IllegalArgumentException("Invalid position: " + position);
        if (size < 0)
            throw new IllegalArgumentException("Invalid size: " + size);

        final int end;
        // handle integer overflow
        if (this.start + position + size < 0)
            end = sizeInBytes();
        else
            end = Math.min(this.start + position + size, sizeInBytes());
        return new FileRecords(file, channel, this.start + position, end, true);
    }