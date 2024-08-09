public void deframe(HttpData data, boolean endOfStream) {
        requireNonNull(data, "data");
        checkNotClosed();
        checkState(!this.endOfStream, "Past end of stream");

        startedDeframing = true;

        final int dataLength = data.length();
        if (dataLength != 0) {
            final ByteBuf buf;
            if (data instanceof ByteBufHolder) {
                buf = ((ByteBufHolder) data).content();
            } else {
                buf = Unpooled.wrappedBuffer(data.array(), data.offset(), dataLength);
            }
            assert unprocessed != null;
            unprocessed.add(buf);
            unprocessedBytes += dataLength;
        }

        // Indicate that all of the data for this stream has been received.
        this.endOfStream = endOfStream;
        deliver();
    }