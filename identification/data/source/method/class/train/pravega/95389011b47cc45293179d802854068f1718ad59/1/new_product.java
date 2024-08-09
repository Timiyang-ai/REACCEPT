public void runAsync() {
        // Determine if a task is running. If so, record the fact we want to have it run again, otherwise reserve our spot.
        synchronized (this) {
            Exceptions.checkNotClosed(this.closed, this);
            if (this.running) {
                this.runAgain = true;
                return;
            }

            this.running = true;
        }

        // Execute the task.
        runInternal();
    }