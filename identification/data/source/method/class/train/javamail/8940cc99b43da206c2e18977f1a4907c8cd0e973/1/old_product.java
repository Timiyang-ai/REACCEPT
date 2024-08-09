public void close() {
        MessageContext ctx = null;
        synchronized (this) {
            super.setLevel(Level.OFF); //security check first.
            try {
                if (size > 0) {
                    ctx = writeLogRecords(ErrorManager.CLOSE_FAILURE);
                }
            } finally {
                /**
                 * The sign bit of the capacity is set to ensure that records
                 * that have passed isLoggable, but have yet to be added to the
                 * internal buffer, are immediately pushed as an email.
                 */
                if (this.capacity > 0) {
                    this.capacity = -this.capacity;
                }

                if (size == 0 && data.length != 1) { //ensure not inside a push.
                    this.data = new LogRecord[1];
                }
            }
        }

        if (ctx != null) {
            send(ctx, false, ErrorManager.CLOSE_FAILURE);
        }
    }