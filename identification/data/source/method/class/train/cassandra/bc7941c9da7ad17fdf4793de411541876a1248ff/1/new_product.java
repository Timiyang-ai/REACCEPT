public void resetAndTruncate(DataPosition mark)
    {
        assert mark instanceof BufferedFileWriterMark;

        long previous = current();
        long truncateTarget = ((BufferedFileWriterMark) mark).pointer;

        // If we're resetting to a point within our buffered data, just adjust our buffered position to drop bytes to
        // the right of the desired mark.
        if (previous - truncateTarget <= buffer.position())
        {
            buffer.position(buffer.position() - ((int) (previous - truncateTarget)));
            return;
        }

        // synchronize current buffer with disk - we don't want any data loss
        syncInternal();

        // truncate file to given position
        truncate(truncateTarget);

        try
        {
            fchannel.position(truncateTarget);
        }
        catch (IOException e)
        {
            throw new FSReadError(e, getPath());
        }

        bufferOffset = truncateTarget;
        resetBuffer();
    }