@Deprecated
    public static String toPath(List<String> path, @Nullable String... extraPath) {
        StringBuilder sb = new StringBuilder();
        {
            int size = path.size();
            for (int i = 0; i < size; i++) {
                sb.append(path.get(i));
                if (i < size - 1) {
                    sb.append(PATH_SEPARATOR);
                }
            }
        }
        {
            if (extraPath != null && extraPath.length > 0) {
                sb.append(PATH_SEPARATOR);
                for (int i = 0; i < extraPath.length; i++) {
                    checkArgument(extraPath[i] != null, "extra path cannot have null elements");
                    sb.append(extraPath[i]);
                    if (i < extraPath.length - 1) {
                        sb.append(PATH_SEPARATOR);
                    }
                }
            }
        }
        return sb.toString();
    }