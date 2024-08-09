@Override
    protected void setup(final SAMFileHeader header, final File samFile) {
        IOUtil.assertFileIsWritable(CHART_OUTPUT);
        if (SUMMARY_OUTPUT != null) IOUtil.assertFileIsWritable(SUMMARY_OUTPUT);

        try{
            saveHeader = header.getReadGroups().get(0).getLibrary();
        } catch (Exception ignored){
            saveHeader = "LibraryNameUnavailable";
        }
    }