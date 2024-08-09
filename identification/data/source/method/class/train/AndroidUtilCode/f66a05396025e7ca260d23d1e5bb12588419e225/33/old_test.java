    @Test
    public void readFile2String() {
        writeFileFromString();
        System.out.println(FileIOUtils.readFile2String(PATH_TEMP + "writeFileFromString.txt"));
    }