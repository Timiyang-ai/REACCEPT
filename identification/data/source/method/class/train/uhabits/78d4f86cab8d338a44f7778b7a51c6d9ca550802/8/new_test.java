    @Test
    public void test_writeCSV() throws IOException
    {
        String expectedCSV = "2015-01-25,2\n2015-01-24,0\n2015-01-23,1\n" +
                             "2015-01-22,2\n2015-01-21,2\n2015-01-20,2\n" +
                             "2015-01-19,1\n2015-01-18,1\n2015-01-17,2\n" +
                             "2015-01-16,2\n";


        StringWriter writer = new StringWriter();
        nonDailyHabit.getCheckmarks().writeCSV(writer);

        assertThat(writer.toString(), equalTo(expectedCSV));
    }