@Override
    public List<AbstractRecord> parseRecords(byte[] rawRecordData) {

        List<AbstractRecord> records = new LinkedList<>();
        int dataPointer = 0;
        while (dataPointer != rawRecordData.length) {
            try {
                RecordParser parser = new RecordParser(dataPointer, rawRecordData,
                        tlsContext.getSelectedProtocolVersion());
                Record record = parser.parse();
                records.add(record);
                dataPointer = parser.getPointer();
            } catch (ParserException E) {
                // TODO Could not parse as record try parsing Blob
            }
        }
        LOGGER.debug("The protocol message(s) were collected from {} record(s). ", records.size());
        return records;
    }