public void remove(ActiveMQDestination key, Object value) {
        synchronized (this) {
            unsynchronizedRemove(key, value);
        }
    }