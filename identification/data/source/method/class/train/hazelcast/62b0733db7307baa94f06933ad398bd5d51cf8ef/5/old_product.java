public static void onOutOfMemory(OutOfMemoryError outOfMemoryError) {
        isNotNull(outOfMemoryError, "outOfMemoryError");

        OutOfMemoryHandler h = clientHandler;
        if (h != null && h.shouldHandle(outOfMemoryError)) {
            try {
                HazelcastInstance[] clients = removeRegisteredClients();
                h.onOutOfMemory(outOfMemoryError, clients);
            } catch (Throwable ignored) {
                EmptyStatement.ignore(ignored);
            }
        }

        h = handler;
        if (h != null && h.shouldHandle(outOfMemoryError)) {
            try {
                HazelcastInstance[] instances = removeRegisteredServers();
                h.onOutOfMemory(outOfMemoryError, instances);
            } catch (Throwable ignored) {
                EmptyStatement.ignore(ignored);
            }
        }
    }