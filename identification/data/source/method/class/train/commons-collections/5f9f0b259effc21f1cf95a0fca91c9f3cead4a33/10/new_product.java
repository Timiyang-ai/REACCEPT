public static Object get(Object object, int index) {
        int i = index;
		if (i < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + i);
        }
        if (object instanceof Map) {
            Map map = (Map) object;
            Iterator iterator = map.entrySet().iterator();
            return get(iterator, i);
        } else if (object instanceof Object[]) {
            return ((Object[]) object)[i];
        } else if (object instanceof Iterator) {
            Iterator it = (Iterator) object;
            while (it.hasNext()) {
                i--;
                if (i == -1) {
                    return it.next();
                }
				it.next();
            }
            throw new IndexOutOfBoundsException("Entry does not exist: " + i);
        } else if (object instanceof Collection) {
            Iterator iterator = ((Collection) object).iterator();
            return get(iterator, i);
        } else if (object instanceof Enumeration) {
            Enumeration it = (Enumeration) object;
            while (it.hasMoreElements()) {
                i--;
                if (i == -1) {
                    return it.nextElement();
                } else {
                    it.nextElement();
                }
            }
            throw new IndexOutOfBoundsException("Entry does not exist: " + i);
        } else if (object == null) {
            throw new IllegalArgumentException("Unsupported object type: null");
        } else {
            try {
                return Array.get(object, i);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
            }
        }
    }