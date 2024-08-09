public static void insertStringInFile(File inputFile, String contentTobeAdded) throws IOException {
        FileWriter fileWriter = new FileWriter(inputFile, true);
        PrintWriter outputPrintWriter = new PrintWriter(fileWriter);
        outputPrintWriter.write(contentTobeAdded);
        outputPrintWriter.flush();
        outputPrintWriter.close();

    }