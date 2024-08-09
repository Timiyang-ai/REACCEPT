public Select.Builder select(String... columns) {
        return new Select.Builder(cluster, Arrays.asList((Object[])columns));
    }