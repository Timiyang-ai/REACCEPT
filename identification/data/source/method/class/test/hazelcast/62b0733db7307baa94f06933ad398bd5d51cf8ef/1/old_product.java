public static void onOutOfMemory(OutOfMemoryError outOfMemoryError) {
        isNotNull(outOfMemoryError, "outOfMemoryError");

        if (handlers.isEmpty()) {
            return;
        }

        HazelcastInstance[] instances = removeRegisteredInstances();
        if (instances.length == 0) {
            return;
        }

        for (OutOfMemoryHandler handler : handlers) {
            try {
                handler.onOutOfMemory(outOfMemoryError, instances);
            } catch (Throwable ignored) {
            }
        }

    }