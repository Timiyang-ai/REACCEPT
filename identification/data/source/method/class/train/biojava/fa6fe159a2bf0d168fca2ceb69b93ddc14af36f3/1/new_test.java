@Test
    public void testToPDB_0args() {
//        System.out.println("toPDB_0args");
        String expResult =  "SITE     1 AC1  6 ARG H 221A LYS H 224  HOH H 403  HOH H 460                    " + newline +
                            "SITE     2 AC1  6 HOH H 464  HOH H 497                                          "+ newline;
        String result = bindingSite.toPDB();
//        System.out.println(result);
        assertEquals(expResult, result);
    }