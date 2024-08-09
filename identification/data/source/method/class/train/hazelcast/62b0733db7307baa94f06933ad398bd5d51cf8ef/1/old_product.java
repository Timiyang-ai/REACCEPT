public static void onOutOfMemory(OutOfMemoryError outOfMemoryError) {
        isNotNull(outOfMemoryError, "outOfMemoryError");

        OutOfMemoryHandler h = handler;
        if (h == null) {
            return;
        }

        HazelcastInstance[] instances = removeRegisteredInstances();
        if (instances.length == 0) {
            return;
        }

        try {
            h.onOutOfMemory(outOfMemoryError, instances);
        } catch (Throwable ignored) {
        }
    }