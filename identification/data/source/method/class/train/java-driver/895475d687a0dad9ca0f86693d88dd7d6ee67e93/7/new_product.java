public static Select.Builder select(String... columns) {
        return new Select.Builder(Arrays.asList(columns));
    }