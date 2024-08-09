public void pretouch(MappedBytes bytes) {
        final long pos;
        try {
            pos = posSupplier.getAsLong();
        } catch (NullPointerException npe) {
            throw new IllegalStateException("Encountered an NPE, possibly because the store was released by something else", npe);
        }
        // don't retain the bytes object when it is head so keep the hashCode instead.
        // small risk of a duplicate hashCode.
        int pageSize = OS.pageSize();
        if (lastBytesHashcode != System.identityHashCode(bytes)) {
            lastTouchedPage = pos - pos % pageSize;
            lastTouchedPos = pos;
            lastBytesHashcode = System.identityHashCode(bytes);
            averageMove = OS.pageSize();
            lastPos = pos;
            if (Jvm.isDebugEnabled(getClass())) {
                String message = getFile(bytes) + " - Reset pretoucher to pos " + pos + " as the underlying MappedBytes changed.";
                debug(message);
            }

        } else {
            long moved = pos - lastPos;
            averageMove = moved / 4 + averageMove * 3 / 4;
            long neededHeadRoom = Math.max(minHeadRoom, averageMove * 4); // for the next 4 ticks.
            final long neededEnd = pos + neededHeadRoom;
            if (lastTouchedPage < neededEnd) {
                Thread thread = Thread.currentThread();
                int count = 0, pretouch = 0;
                for (; lastTouchedPage < neededEnd; lastTouchedPage += pageSize) {
                    if (thread.isInterrupted())
                        break;
                    if (touchPage(bytes, lastTouchedPage))
                        pretouch++;
                    count++;
                }
                onTouched(count);
                if (pretouch < count) {
                    minHeadRoom += 256 << 10;
                    if (Jvm.isDebugEnabled(getClass()))
                        debug("pretouch for only " + pretouch + " of " + count + " min: " + (minHeadRoom >> 20) + " MB.");
                }

                long pos2 = posSupplier.getAsLong();
                if (Jvm.isDebugEnabled(getClass())) {
                    String message = getFile(bytes) + ": Advanced " + (pos - lastTouchedPos) / 1024 + " KB, " +
                            "avg " + averageMove / 1024 + " KB " +
                            "between pretouch() and " + (pos2 - pos) / 1024 + " KB " +
                            "while mapping of " + pretouch * pageSize / 1024 + " KB ";
                    debug(message);
                }
                lastTouchedPos = pos;
            }
            lastPos = pos;
        }
    }