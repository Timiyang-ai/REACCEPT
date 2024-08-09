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
            if (unprocessed != null) {
                unprocessed.addComponent(true, buf);
            } else if (firstFrame == null) {
                firstFrame = buf;
            } else {
                unprocessed = alloc.compositeBuffer();
                unprocessed.addComponent(true, firstFrame);
                unprocessed.addComponent(true, buf);
                firstFrame = null;
            }
        }

        // Indicate that all of the data for this stream has been received.
        this.endOfStream = endOfStream;
        deliver();
    }