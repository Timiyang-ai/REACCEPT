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
        this.executor.execute(() -> {
            boolean canContinue = true;
            while (canContinue) {
                try {
                    this.runnable.run();
                } finally {
                    // Determine if we need to run the task again. Otherwise release our spot.
                    synchronized (this) {
                        canContinue = this.runAgain && !this.closed;
                        this.runAgain = false;
                        this.running = canContinue;
                    }
                }
            }
        });
    }