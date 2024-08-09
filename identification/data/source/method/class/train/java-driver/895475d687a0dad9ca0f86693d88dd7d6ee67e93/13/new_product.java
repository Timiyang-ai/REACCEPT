public static Delete.Builder delete(String... columns) {
        return new Delete.Builder(Arrays.asList((Object[])columns));
    }