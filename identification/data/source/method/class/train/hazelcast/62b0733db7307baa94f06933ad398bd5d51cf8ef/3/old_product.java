public synchronized static void onOutOfMemory(OutOfMemoryError oom) {
        if (handler != null) {
            try {
                handler.onOutOfMemory(oom, instances);
            } catch (Throwable ignored) {
            }
        }
    }