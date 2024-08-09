    private OutputMapper mapper( Class<?> clazz ) throws ProcedureException
    {
        return new OutputMappers( new TypeMappers() ).mapper( clazz );
    }