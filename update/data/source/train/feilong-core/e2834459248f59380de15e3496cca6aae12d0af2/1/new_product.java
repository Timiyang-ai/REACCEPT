public static boolean isAssignableFrom(Class<?> klass,Class<?> cls){
        return (null == klass || null == cls) ? false : klass.isAssignableFrom(cls);
    }