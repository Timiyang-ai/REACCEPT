public static Object get(Object object, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
        }
        if (object instanceof Map) {
            Map map = (Map) object;
            Iterator iterator = map.entrySet().iterator();
            return get(iterator, index);
        } else if (object instanceof List) {
            return ((List) object).get(index);
        } else if (object instanceof Object[]) {
            return ((Object[]) object)[index];
        } else if (object instanceof Enumeration) {
            Enumeration it = (Enumeration) object;
            while (it.hasMoreElements()) {
                index--;
                if (index == -1) {
                    return it.nextElement();
                } else {
                    it.nextElement();
                }
            }
            throw new IndexOutOfBoundsException("Entry does not exist: " + index);
        } else if (object instanceof Iterator) {
            Iterator it = (Iterator) object;
            while (it.hasNext()) {
                index--;
                if (index == -1) {
                    return it.next();
                } else {
                    it.next();
                }
            }
            throw new IndexOutOfBoundsException("Entry does not exist: " + index);
        } else if (object instanceof Collection) {
            Iterator iterator = ((Collection) object).iterator();
            return get(iterator, index);
        } else {
            throw new IllegalArgumentException("Unsupported object type: " +
                (object == null ? "null" : object.getClass().getName()));
        }
    }