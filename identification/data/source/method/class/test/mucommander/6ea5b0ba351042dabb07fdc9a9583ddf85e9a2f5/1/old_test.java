    @DataProvider(name = "endsWithIgnoreCase")
    public Iterator<Object[]> endsWithIgnoreCaseTestCases() {
        List<Object[]> data;
        
        data = new ArrayList<Object[]>();
        
        data.add(new Object[] {"this is a test", "a test", true});
        data.add(new Object[] {"this is a test", "a TeSt", true});
        data.add(new Object[] {"this is a test", "A TEST", true});

        data.add(new Object[] {"THIS IS A TEST", "a test", true});
        data.add(new Object[] {"THIS IS A TEST", "a TeSt", true});
        data.add(new Object[] {"THIS IS A TEST", "A TEST", true});

        data.add(new Object[] {"ThIs Is A TeSt", "a test", true});
        data.add(new Object[] {"ThIs Is A TeSt", "a TeSt", true});
        data.add(new Object[] {"ThIs Is A TeSt", "A TEST", true});

        data.add(new Object[] {"this is a test", "this is a test", true});
        data.add(new Object[] {"this is a test", "ThIs Is A TeSt", true});
        data.add(new Object[] {"this is a test", "THIS IS A TEST", true});

        data.add(new Object[] {"THIS IS A TEST", "this is a test", true});
        data.add(new Object[] {"THIS IS A TEST", "ThIs Is A TeSt", true});
        data.add(new Object[] {"THIS IS A TEST", "THIS IS A TEST", true});

        data.add(new Object[] {"ThIs Is A TeSt", "this is a test", true});
        data.add(new Object[] {"ThIs Is A TeSt", "ThIs Is A TeSt", true});
        data.add(new Object[] {"ThIs Is A TeSt", "THIS IS A TEST", true});

        data.add(new Object[] {"this is a test", "", true});
        
        data.add(new Object[] {"this is a test", "test a is this", false});
        data.add(new Object[] {"this is a test", "tEsT a Is ThIs", false});
        data.add(new Object[] {"this is a test", "TEST A IS THIS", false});

        data.add(new Object[] {"THIS IS A TEST", "test a is this", false});
        data.add(new Object[] {"THIS IS A TEST", "tEsT a Is ThIs", false});
        data.add(new Object[] {"THIS IS A TEST", "TEST A IS THIS", false});

        data.add(new Object[] {"ThIs Is A tEst", "test a is this", false});
        data.add(new Object[] {"ThIs Is A tEst", "tEsT a Is ThIs", false});
        data.add(new Object[] {"ThIs Is A tEst", "TEST A IS THIS", false});
        
        return data.iterator();
    }