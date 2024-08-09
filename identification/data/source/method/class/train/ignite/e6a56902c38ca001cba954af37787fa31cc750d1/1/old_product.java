void cancel(boolean forceQryCancel) {
        if (cancelled)
            return;

        cancelled = true;

        for (int i = 0; i < results.length(); i++) {
            MapQueryResult res = results.get(i);

            if (res != null) {
                res.close();

                continue;
            }

            // NB: Cancel is already safe even for lazy queries (see implementation of passed Runnable).
            if (forceQryCancel) {
                GridQueryCancel cancel = cancels[i];

                if (cancel != null)
                    cancel.cancel();
            }
        }
    }