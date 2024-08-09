@Test public void read_StringReader_31()
    {
        String x = "<s> <p> <p> ." ;
        
        {
            StringReader s = new StringReader(x) ;
            Model m = ModelFactory.createDefaultModel() ;
            RDFDataMgr.read(m, s, null, RDFLanguages.NTRIPLES) ;
        }
        
        StringReader s1 = new StringReader("<s> <p> <p> .") ;
        Model m1 = ModelFactory.createDefaultModel() ;
        m1.read(s1, null, "N-TRIPLES") ;
    }