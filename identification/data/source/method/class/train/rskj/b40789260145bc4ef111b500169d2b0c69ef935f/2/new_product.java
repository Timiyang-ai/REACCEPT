public void recordEvent(EventType evt) {
        try {
            rwlock.writeLock().lock();

            counters[evt.ordinal()]++;

            switch (evt) {
                case INVALID_NETWORK:
                case INVALID_BLOCK:
                case INVALID_TRANSACTION:
                case INVALID_MESSAGE:
                case TIMEOUT_MESSAGE:
                    if (score > 0)
                        score = 0;
                    score--;
                    break;

                case UNEXPECTED_MESSAGE:
                case FAILED_HANDSHAKE:
                case SUCCESSFUL_HANDSHAKE:
                case REPEATED_MESSAGE:
                    break;

                default:
                    if (score >= 0)
                        score++;
                    break;
            }
        } finally {
            rwlock.writeLock().unlock();
        }
    }