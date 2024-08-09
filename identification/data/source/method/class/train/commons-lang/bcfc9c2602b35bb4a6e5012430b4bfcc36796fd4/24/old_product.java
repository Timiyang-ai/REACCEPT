public static boolean isAssignable(Class cls, Class toClass) {
        if (toClass == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        // have to check for null, as isAssignableFrom doesn't
        if (cls == null) {
            return !(toClass.isPrimitive());
        }
        if (cls.equals(toClass)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (toClass.isPrimitive() == false) {
                return false;
            }
            if (Integer.TYPE.equals(cls)) {
                return Long.TYPE.equals(toClass) 
                    || Float.TYPE.equals(toClass) 
                    || Double.TYPE.equals(toClass);
            }
            if (Long.TYPE.equals(cls)) {
                return Float.TYPE.equals(toClass) 
                    || Double.TYPE.equals(toClass);
            }
            if (Boolean.TYPE.equals(cls)) {
                return false;
            }
            if (Double.TYPE.equals(cls)) {
                return false;
            }
            if (Float.TYPE.equals(cls)) {
                return Double.TYPE.equals(toClass);
            }
            if (Character.TYPE.equals(cls)) {
                return Integer.TYPE.equals(toClass) 
                    || Long.TYPE.equals(toClass) 
                    || Float.TYPE.equals(toClass) 
                    || Double.TYPE.equals(toClass);
            }
            if (Short.TYPE.equals(cls)) {
                return Integer.TYPE.equals(toClass) 
                    || Long.TYPE.equals(toClass) 
                    || Float.TYPE.equals(toClass) 
                    || Double.TYPE.equals(toClass);
            }
            if (Byte.TYPE.equals(cls)) {
                return Short.TYPE.equals(toClass) 
                    || Integer.TYPE.equals(toClass) 
                    || Long.TYPE.equals(toClass) 
                    || Float.TYPE.equals(toClass) 
                    || Double.TYPE.equals(toClass);
            }
            // should never get here
            return false;
        }
        return toClass.isAssignableFrom(cls);
    }