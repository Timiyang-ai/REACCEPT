void cancel() {
        synchronized (this) {
            if (cancelled)
                return;

            cancelled = true;

            for (int i = 0; i < results.length(); i++) {
                GridQueryCancel cancel = cancels[i];

                if (cancel != null)
                    cancel.cancel();
            }
        }

        // The closing result set is synchronized by themselves.
        // Include to synchronize block may be cause deadlock on <this> and MapQueryResult#lock.
        close();
    }