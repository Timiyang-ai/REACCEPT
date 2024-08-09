public int compareTo(OffsetDate other) {
        if (offset.equals(other.offset)) {
            return date.compareTo(other.date);
        }
        int compare = Long.compare(toEpochSecond(), other.toEpochSecond());
        if (compare == 0) {
            compare = date.compareTo(other.date);
        }
        return compare;
    }