    private static int rank(ResolvedJavaType type) {
        String name = type.getName();
        int dims = 0;
        while (dims < name.length() && name.charAt(dims) == '[') {
            dims++;
        }
        return dims;
    }