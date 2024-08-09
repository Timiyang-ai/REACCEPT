    public void setupTest1(final int ID, final String readGroupId, final SAMReadGroupRecord readGroupRecord, final String sample,
                      final String library, final SAMFileHeader header, final SAMRecordSetBuilder setBuilder)
            throws IOException {

        final String separator = ":";
        final int contig1 = 0;
        final int contig2 = 1;
        readGroupRecord.setSample(sample);
        readGroupRecord.setPlatform(platform);
        readGroupRecord.setLibrary(library);
        readGroupRecord.setPlatformUnit(readGroupId);
        header.addReadGroup(readGroupRecord);
        setBuilder.setReadGroup(readGroupRecord);
        setBuilder.setUseNmFlag(true);

        setBuilder.setHeader(header);

        final int max = 800;
        final int min = 1;
        final Random rg = new Random(5);

        //add records that align to chrM and O but not N
        for (int i = 0; i < NUM_READS; i++) {
            final int start = rg.nextInt(max) + min;
            final String newReadName = READ_NAME + separator + ID + separator + i;

            if (i != NUM_READS - 1) {
                setBuilder.addPair(newReadName, contig1, start + ID, start + ID + LENGTH);
            } else {
                setBuilder.addPair(newReadName, contig2, start + ID, start + ID + LENGTH);
            }
        }
    }