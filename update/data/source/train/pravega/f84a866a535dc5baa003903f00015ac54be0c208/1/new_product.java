public void truncate(long upToSequenceNumber) {
        synchronized (this.lock) {
            // We truncate by finding the new head and simply pointing our head reference to it, as well as disconnecting
            // its predecessor node from it. We also need to mark every truncated node as such - this will instruct ongoing
            // reads to stop serving truncated data.
            while (this.head != null && this.head.item.getSequenceNumber() <= upToSequenceNumber) {
                this.head = trim(this.head);
            }

            if (this.head == null) {
                this.tail = null;
            }
        }
    }