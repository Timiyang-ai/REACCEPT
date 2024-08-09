public void recordEvent(EventType evt) {
        if (!counters.containsKey(evt))
            counters.put(evt, 1);
        else
            counters.put(evt, counters.get(evt).intValue() + 1);

        switch (evt) {
            case INVALID_NETWORK:
            case INVALID_BLOCK:
            case INVALID_TRANSACTION:
                if (score > 0)
                    score = 0;
                score--;
                break;

            case FAILED_HANDSHAKE:
            case SUCCESSFUL_HANDSHAKE:
            case REPEATED_MESSAGE:
                break;

            default:
                if (score >= 0)
                    score++;
                break;
        }
    }