@Test
    public void testSplitCmdLine() throws CloudServiceException {
        final String input = "-f ch_survey_response_f.pig -p vhs_window_start_date=20120912 "
                + "-p survey_date=20120919 -p now_epoch_ts=1348125009 "
                + "-p rap_comment='/**/' -p no_rap_comment='--'";
        final String[] output = StringUtil.splitCmdLine(input);
        Assert.assertTrue(12 == output.length);
    }