@Nullable
    public static Object getDeclaredField(Object obj, String fieldName) {
        try {
            Field f = getField(obj.getClass(), fieldName);
            if (f == null) {
                return null;
            }
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }