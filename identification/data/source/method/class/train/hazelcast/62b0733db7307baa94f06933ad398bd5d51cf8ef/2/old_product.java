public static void onOutOfMemory(OutOfMemoryError outOfMemoryError) {
        isNotNull(outOfMemoryError, "outOfMemoryError");

        HazelcastInstance[] instances = removeRegisteredInstances();
        if (instances.length == 0) {
            return;
        }

        OutOfMemoryHandler h = clientHandler;
        if (h != null) {
            try {
                h.onOutOfMemory(outOfMemoryError, instances);
            } catch (Throwable ignored) {
            }
        }

        h = handler;
        if (h != null) {
            try {
                h.onOutOfMemory(outOfMemoryError, instances);
            } catch (Throwable ignored) {
            }
        }
    }