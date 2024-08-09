public String print(Calendrical calendrical) {
        StringBuilder buf = new StringBuilder(32);
        print(calendrical, buf);
        return buf.toString();
    }