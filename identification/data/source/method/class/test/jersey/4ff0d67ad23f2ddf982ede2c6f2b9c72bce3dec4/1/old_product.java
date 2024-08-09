public static Class[] getParameterizedClassArguments(DeclaringClassInterfacePair p) {
        if (p.genericInterface instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) p.genericInterface;
            Type[] as = pt.getActualTypeArguments();
            Class[] cas = new Class[as.length];

            for (int i = 0; i < as.length; i++) {
                Type a = as[i];
                if (a instanceof Class) {
                    cas[i] = (Class) a;
                } else if (a instanceof ParameterizedType) {
                    pt = (ParameterizedType) a;
                    cas[i] = (Class) pt.getRawType();
                } else if (a instanceof TypeVariable) {
                    ClassTypePair ctp = resolveTypeVariable(p.concreteClass, p.declaringClass, (TypeVariable) a);
                    cas[i] = (ctp != null) ? ctp.rawClass() : Object.class;
                }
            }
            return cas;
        } else {
            return null;
        }
    }