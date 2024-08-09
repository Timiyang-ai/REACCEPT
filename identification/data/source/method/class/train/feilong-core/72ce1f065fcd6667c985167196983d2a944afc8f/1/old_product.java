public static boolean isInstance(Object obj,Class<?>[] klasses){
        if (null == klasses){
            return false;
        }

        for (Class<?> klass : klasses){
            if (isInstance(obj, klass)){
                return true;
            }
        }
        return false;
    }