@Test
    public void testRead() {
        String[] expResult = {"ints	Strings	Dates	Not quite Dates	Numbers	Not quite Ints	Not quite Numbers	Column that hates you and is so long that things might start breaking because we previously had a 255 character limit on length for things even when that might not be completely justified. Wow this is an increadibly long header name. Who made this? Oh, that's right, I did.",
            "-199	\"hello\"	2017-06-20	\"2017/06/20\"	0	1	\"Nan\"	\"823478788778713\"",
            "2	\"Sdfwer\"	2017-06-20	\"1100/06/20\"	Inf	2	\"2\"	\",1,2,3\"",
            "0	\"cjlajfo.\"	2017-06-20	\"3000/06/20\"	-Inf	3	\"inf\"	\"\\casdf\"",
            "-1	\"Mywer\"	2017-06-20	\"06-20-2011\"	3.141592653	4	\"4.8\"	\"asd\"",
            "266128	\"Sf\"	2017-06-20	\"06-20-1917\"	0	5	\"Inf+11\"	\"\"",
            "123	\"werxc\"	2017-06-20	\"03/03/1817\"	123	6.000001	\"11-2\"	\"adf\\\"\\0\\na\\tdsf\"",
            "-2389	\"Dfjl\"	2017-06-20	\"2017-03-12\"	NaN	2	\"nap\"	\"💩⌛👩🏻■\""};
        BufferedReader result = null;
        try (BufferedInputStream stream = new BufferedInputStream(
                new FileInputStream(testFile))) {
            CSVFileReader instance = new CSVFileReader(new CSVFileReaderSpi());
            result = new BufferedReader(new FileReader(instance.read(stream, null).getTabDelimitedFile()));
        } catch (IOException ex) {
            fail("" + ex);
        }

        String foundLine = null;
        assertNotNull(result);
        for (String expLine : expResult) {
            try {
                foundLine = result.readLine();
            } catch (IOException ex) {
                fail();
            }
            if(!expLine.equals(foundLine)) {
                logger.info("expected: " + expLine);
                logger.info("found : " + foundLine);
            }
            assertEquals(expLine, foundLine);
        }

    }