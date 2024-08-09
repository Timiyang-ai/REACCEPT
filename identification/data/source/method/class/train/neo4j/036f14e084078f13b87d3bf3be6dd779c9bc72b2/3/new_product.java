public OutputMapper mapper( Method method ) throws ProcedureException
    {
        Class<?> cls = method.getReturnType();
        if( cls == Void.class || cls == void.class )
        {
            return OutputMappers.VOID_MAPPER;
        }

        if ( cls != Stream.class )
        {
            throw invalidReturnType( cls );
        }

        Type genericReturnType = method.getGenericReturnType();
        if (!(genericReturnType instanceof ParameterizedType))
        {
            throw new ProcedureException( Status.Procedure.TypeError,
                    "Procedures must return a Stream of records, where a record is a concrete class%n" +
                    "that you define and not a raw Stream.");
        }

        ParameterizedType genType = (ParameterizedType) genericReturnType;
        Type recordType = genType.getActualTypeArguments()[0];
        if ( recordType instanceof WildcardType)
        {
            throw new ProcedureException( Status.Procedure.TypeError,
                    "Procedures must return a Stream of records, where a record is a concrete class%n" +
                    "that you define and not a Stream<?>.");
        }
        if (recordType instanceof ParameterizedType)
        {
            ParameterizedType type = (ParameterizedType) recordType;
            throw new ProcedureException( Status.Procedure.TypeError,
                    "Procedures must return a Stream of records, where a record is a concrete class%n" +
                    "that you define and not a parameterized type such as %s.", type);
        }

        return mapper( (Class<?>) recordType );
    }