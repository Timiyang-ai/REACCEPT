public static Class[] getParameterizedClassArguments(final DeclaringClassInterfacePair p) {
        if (p.genericInterface instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) p.genericInterface;
            final Type[] as = pt.getActualTypeArguments();
            final Class[] cas = new Class[as.length];

            for (int i = 0; i < as.length; i++) {
                final Type a = as[i];
                if (a instanceof Class) {
                    cas[i] = (Class) a;
                } else if (a instanceof ParameterizedType) {
                    pt = (ParameterizedType) a;
                    cas[i] = (Class) pt.getRawType();
                } else if (a instanceof TypeVariable) {
                    final TypeVariable tv = (TypeVariable) a;
                    final ClassTypePair ctp = resolveTypeVariable(p.concreteClass, p.declaringClass, tv);
                    cas[i] = (ctp != null) ? ctp.rawClass() : (Class<?>) (tv.getBounds()[0]);
                } else if (a instanceof GenericArrayType) {
                    final GenericArrayType gat = (GenericArrayType) a;
                    final Type t = gat.getGenericComponentType();
                    if (t instanceof Class) {
                        cas[i] = getArrayForComponentType((Class<?>) t);
                    }
                }
            }
            return cas;
        } else {
            return null;
        }
    }