public static byte[] extractData(DatagramPacket message) throws InvalidGELFHeaderException, IOException {
        if (message.getLength() <= GELF.GELF_HEADER_LENGTH) {
            throw new InvalidGELFHeaderException();
        }

        byte[] data = new byte[GELF.GELF_DATA_PART_MAX_LENGTH];

        int j = 0;
        for (int i = GELF.GELF_HEADER_LENGTH; i < GELF.GELF_DATA_PART_MAX_LENGTH+GELF.GELF_HEADER_LENGTH; i++) {
            data[j] = message.getData()[i];
            j++;
        }

        return data;
    }