@Test
    public void testRead() throws IOException {
        String[] expResult = {"ints	Strings	Dates	Numbers	Not quite Ints	Not quite Numbers	Column that hates you and is so long that things might start breaking because we previously had a 255 character limit on length for things even when that might not be completely justified. Wow this is an increadibly long header name. Who made this? Oh, that's right, I did.",
                              "-199	\"hello\"	\"06/20/17\"	1	1	\"Nan\"	\"adf\\\"\\0\\nadsf\"",
                              "2	\"Sdfwer\"	\"06/20/17\"	Inf	2	\"2\"	\",1,2,3\"",
                              "0	\"cjlajfo.\"	\"06/20/17\"	-Inf	3	\"inf\"	\"\\casdf\"",
                              "-1	\"Mywer\"	\"06/20/17\"	3.141592653	4	\"4.8\"	\"asd\"",
                              "266128	\"Sf\"	\"06/20/17\"	0	5	\"Inf+11\"	\"\"",
                              "123	\"werxc\"	\"03/03/17\"	123	6.000001	\"11-2\"	\"823478788778713\"",
                              "-2389	\"Dfjl\"	\"2017/03/12\"	NaN	2	\"nap\"	\"asdf\""};
        BufferedReader result = null;
        try (BufferedInputStream stream = new BufferedInputStream(
                                          new FileInputStream(testFile))) {
            CSVFileReader instance = new CSVFileReader(new CSVFileReaderSpi());
            result = new BufferedReader(new FileReader(instance.read(stream, null).getTabDelimitedFile()));
        }
        catch (IOException ex) {
            fail(""+ex);
        }
        assertNotNull(result);
        for (String expLine : expResult)
        {
            String foundLine = result.readLine();
            logger.info(expLine);
            logger.info(foundLine);
            assertEquals(expLine, foundLine);
        }
        
    }