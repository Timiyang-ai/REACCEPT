public static void read(Graph graph, StringReader in, String base, Lang lang)
    {
        StreamRDF dest = StreamRDFLib.graph(graph) ;
        processTriples(dest, base, in, lang, null) ;
    }