@Override
    public boolean removeFirstOccurrence(DelayedEntry entry) {
        Data key = (Data) entry.getKey();
        Object value = entry.getValue();
        DelayedEntry delayedEntry = map.get(key);
        if (delayedEntry == null) {
            return false;
        }

        Object existingValue = delayedEntry.getValue();
        if (existingValue.equals(value)) {
            map.remove(key);
            return true;
        }

        return false;
    }