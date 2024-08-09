@Name( "get_all_edges" )
    @Description( "execute a Gremlin script with variables 'start' set to the start node 'g' set to the Neo4jGraph and 'results' containing a resulting vertex" )
    @PluginTarget( GraphDatabaseService.class )
    public Representation getEdges(
            @Description( "The Gremlin script" ) @Parameter( name = "script", optional=true ) String script ,@Source GraphDatabaseService graphDb)
    {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "gremlin" );
        ArrayList<EdgeRepresentation> results = new ArrayList<EdgeRepresentation>();
        Neo4jGraph graph = new Neo4jGraph( graphDb );
        engine.getBindings( ScriptContext.ENGINE_SCOPE ).put( "g",
                graph );
        engine.getBindings( ScriptContext.GLOBAL_SCOPE ).put( "results",
                results );
        try
        {
            engine.eval( script );
        }
        catch ( ScriptException e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ListRepresentation( RepresentationType.RELATIONSHIP, results );
    }