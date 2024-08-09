public String print(CalendricalProvider calendrical) {
        StringBuilder buf = new StringBuilder(32);
        print(calendrical, buf);
        return buf.toString();
    }