@Test
    public void testInvalidateQuery() throws Exception {

        SFSB2LC sfsb = lookup("SFSB2LC", SFSB2LC.class);
        String id = "2";

        String message = sfsb.queryCacheCheck(id);
        
        if (!message.equals("OK")){
            fail(message);
        }
    
        // invalidate the cache
        sfsb.createEmployee("Newman", "Paul", 400);
        
        // the nextTimestamp from infinispan is "return System.currentTimeMillis() / 100;"
        Thread.sleep(1000);
        
        message = sfsb.queryCacheCheck(id);

        if (!message.equals("OK")){
            fail(message);
        }

    }