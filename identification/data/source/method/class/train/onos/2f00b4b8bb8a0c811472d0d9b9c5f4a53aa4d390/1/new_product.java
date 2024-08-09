public static void updateFileHandle(File inputFile, String contentTobeAdded, boolean isClose) throws IOException {
        FileWriter fileWriter = new FileWriter(inputFile, true);
        PrintWriter outputPrintWriter = new PrintWriter(fileWriter);
        if (!isClose) {
            outputPrintWriter.write(contentTobeAdded);
            outputPrintWriter.flush();
            outputPrintWriter.close();
        } else {
            fileWriter.flush();
            fileWriter.close();
        }
    }