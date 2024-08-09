public OutputMapper mapper( Method method ) throws ProcedureException
    {
        Class<?> cls = method.getReturnType();
        if( cls == Void.class || cls == void.class )
        {
            return new OutputMapper( new FieldSignature[0], new FieldMapper[0] );
        }

        if ( cls != Stream.class )
        {
            throw invalidReturnType( cls );
        }

        ParameterizedType genType = (ParameterizedType) method.getGenericReturnType();
        Type recordType = genType.getActualTypeArguments()[0];

        return mapper( (Class<?>) recordType );
    }