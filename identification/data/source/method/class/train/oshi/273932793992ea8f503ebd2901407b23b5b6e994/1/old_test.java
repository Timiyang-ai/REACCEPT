@Test
    public void testReadFile() {
        List<String> thisFile = null;
        try {
            thisFile = FileUtil.readFile(THISCLASS);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // Comment ONE line
        int lineOne = 0;
        // Comment TWO line
        int lineTwo = 0;
        for (int i = 0; i < thisFile.size(); i++) {
            String line = thisFile.get(i);
            if (lineOne == 0 && line.contains("Comment ONE line")) {
                lineOne = i;
                continue;
            }
            if (lineTwo == 0 && line.contains("Comment TWO line")) {
                lineTwo = i;
                break;
            }
        }
        assertEquals(2, lineTwo - lineOne);
    }