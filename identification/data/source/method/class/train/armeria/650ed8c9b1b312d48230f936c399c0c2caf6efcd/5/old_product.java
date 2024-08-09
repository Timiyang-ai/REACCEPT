public void deframe(HttpData data, boolean endOfStream) {
        requireNonNull(data, "data");
        checkNotClosed();
        checkState(!this.endOfStream, "Past end of stream");

        startedDeframing = true;

        if (!data.isEmpty()) {
            final ByteBuf buf;
            if (data instanceof ByteBufHolder) {
                buf = ((ByteBufHolder) data).content();
            } else {
                buf = alloc.buffer(data.length());
                buf.writeBytes(data.array(), data.offset(), data.length());
            }
            assert unprocessed != null;
            unprocessed.addComponent(true, buf);
        }

        // Indicate that all of the data for this stream has been received.
        this.endOfStream = endOfStream;
        deliver();
    }