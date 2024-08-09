@Override  // override for Javadoc and performance
    public int compareTo(ChronoLocalDate<?> other) {
        if (other instanceof LocalDate) {
            return compareTo0((LocalDate) other);
        }
        return ChronoLocalDate.super.compareTo(other);
    }