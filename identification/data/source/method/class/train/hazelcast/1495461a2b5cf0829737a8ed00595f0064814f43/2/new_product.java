@Override
    public boolean removeFirstOccurrence(DelayedEntry incoming) {
        Data incomingKey = (Data) incoming.getKey();
        Object incomingValue = incoming.getValue();

        DelayedEntry current = map.get(incomingKey);
        if (current == null) {
            return false;
        }

        if (current.getSequence() > incoming.getSequence()) {
            // current is newer than incoming: do not remove
            return false;
        }

        Object currentValue = current.getValue();
        if (incomingValue == null && currentValue == null
                || incomingValue != null && currentValue != null && incomingValue.equals(currentValue)) {
            map.remove(incomingKey);
            return true;
        }

        return false;
    }