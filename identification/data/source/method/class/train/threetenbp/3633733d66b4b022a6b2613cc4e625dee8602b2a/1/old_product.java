public String print(DateTimeCalendricalObject calendrical) {
        StringBuilder buf = new StringBuilder(32);
        printTo(calendrical, buf);
        return buf.toString();
    }