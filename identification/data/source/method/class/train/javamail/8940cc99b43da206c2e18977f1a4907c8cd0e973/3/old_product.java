public synchronized void close() {
        super.setLevel(Level.OFF); //security check first.
        push(ErrorManager.CLOSE_FAILURE, false);

        /**
         * The sign bit of the capacity is set to ensure that records that
         * have passed isLoggable, but have yet to be added to the internal
         * buffer, are immediately pushed as an email.
         */
        if (this.capacity > 0) {
            this.capacity = -this.capacity;
            if (this.data.isEmpty()) { //ensure not inside a push.
                this.data = newData(1);
            }
        }
        assert this.capacity < 0;
    }