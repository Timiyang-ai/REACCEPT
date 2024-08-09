public int compareTo(OffsetDate other) {
        if (offset.equals(other.offset)) {
            return date.compareTo(other.date);
        }
        LocalDateTime thisDT = LocalDateTime.midnight(getYear(), getMonthOfYear(), getDayOfMonth());
        LocalDateTime otherDT = LocalDateTime.midnight(other.getYear(), other.getMonthOfYear(), other.getDayOfMonth());
        LocalDateTime thisUTC = thisDT.plusSeconds(-offset.getAmountSeconds());
        LocalDateTime otherUTC = otherDT.plusSeconds(-other.offset.getAmountSeconds());
        int compare = thisUTC.compareTo(otherUTC);
        if (compare == 0) {
            compare = date.compareTo(other.date);
        }
        return compare;
    }