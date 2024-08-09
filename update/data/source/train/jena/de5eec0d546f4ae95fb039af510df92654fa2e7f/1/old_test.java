@Test public void read_31()
    {
        String x = "<s> <p> <p> ." ;
        
        {
            StringReader s = new StringReader(x) ;
            Model m = ModelFactory.createDefaultModel() ;
            WebReader2.read(m, s, null, RDFLanguages.NTriples) ;
        }
        
        StringReader s1 = new StringReader("<s> <p> <p> .") ;
        Model m1 = ModelFactory.createDefaultModel() ;
        m1.read(s1, null, "N-TRIPLES") ;
    }