    @Test
    public void writeFileFromIS() throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(String.format("%5dFileIOUtilsTest\n", i));
        }
        InputStream is = ConvertUtils.string2InputStream(sb.toString(), "UTF-8");

        FileIOUtils.writeFileFromIS(PATH_TEMP + "writeFileFromIS.txt", is, new FileIOUtils.OnProgressUpdateListener() {
            @Override
            public void onProgressUpdate(double progress) {
                System.out.println(String.format("%.2f", progress));
            }
        });
    }