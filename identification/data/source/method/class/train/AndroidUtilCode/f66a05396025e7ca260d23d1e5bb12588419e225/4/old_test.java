    @Test
    public void writeFileFromString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i).append("FileIOUtilsTest\n");
        }
        FileIOUtils.writeFileFromString(PATH_TEMP + "writeFileFromString.txt", sb.toString());
    }