public String print(DateTimeAccessor dateTime) {
        StringBuilder buf = new StringBuilder(32);
        printTo(dateTime, buf);
        return buf.toString();
    }