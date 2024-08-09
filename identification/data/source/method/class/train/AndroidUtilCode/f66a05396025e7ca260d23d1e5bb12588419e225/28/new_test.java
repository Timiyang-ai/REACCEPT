    @Test
    public void readFile2List() {
        writeFileFromString();
        for (String s : FileIOUtils.readFile2List(PATH_TEMP + "writeFileFromString.txt")) {
            System.out.println(s);
        }
    }