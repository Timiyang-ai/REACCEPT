public Class<?> getWrapperType(Class<?> primitiveType) {
        for(PrimitiveEnum p : PrimitiveEnum.values()) {
            if(p.getType().equals(primitiveType)) {
                return p.getClazz();
            }
        }

        return primitiveType; // if not primitive, return it as is
    }