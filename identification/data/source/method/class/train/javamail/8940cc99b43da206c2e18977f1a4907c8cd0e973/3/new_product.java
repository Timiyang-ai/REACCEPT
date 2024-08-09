public void close() {
        Message msg = null;
        synchronized (this) {
            super.setLevel(Level.OFF); //security check first.
            msg = writeLogRecords(false, ErrorManager.CLOSE_FAILURE);
            /**
             * The sign bit of the capacity is set to ensure that records that
             * have passed isLoggable, but have yet to be added to the internal
             * buffer, are immediately pushed as an email.
             */
            if (this.capacity > 0) {
                this.capacity = -this.capacity;
                if (size == 0) { //ensure not inside a push.
                    reset();
                }
            }
            assert this.capacity < 0;
        }
        send(msg, ErrorManager.CLOSE_FAILURE);
    }