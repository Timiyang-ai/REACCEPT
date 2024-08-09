    @Test
    public void writeFileFromBytesByStream() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(String.format("%5dFileIOUtilsTest\n", i));
        }
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);

        FileIOUtils.writeFileFromBytesByStream(PATH_TEMP + "writeFileFromBytesByStream.txt", bytes, new FileIOUtils.OnProgressUpdateListener() {
            @Override
            public void onProgressUpdate(double progress) {
                System.out.println(String.format("%.2f", progress));
            }
        });
    }