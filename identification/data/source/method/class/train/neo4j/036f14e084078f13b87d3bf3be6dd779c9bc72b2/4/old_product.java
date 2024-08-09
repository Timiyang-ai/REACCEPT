public OutputMapper mapper( Method method ) throws ProcedureException
    {
        Class<?> cls = method.getReturnType();
        if ( cls != Stream.class )
        {
            throw new ProcedureException( Status.Procedure.FailedRegistration,
                    "A procedure must return a `java.util.stream.Stream`, `%s.%s` returns `%s`.",
                    method.getDeclaringClass().getSimpleName(), method.getName(), cls.getSimpleName() );
        }

        ParameterizedType genType = (ParameterizedType) method.getGenericReturnType();
        Type recordType = genType.getActualTypeArguments()[0];

        return mapper( (Class<?>) recordType );
    }