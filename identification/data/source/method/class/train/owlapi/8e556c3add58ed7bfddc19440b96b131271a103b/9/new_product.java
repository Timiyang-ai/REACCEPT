public OBODoc parse(File file) throws IOException {
        location = file;
        try (FileInputStream f = new FileInputStream(file);
            InputStreamReader in2 = new InputStreamReader(f, StandardCharsets.UTF_8);
            BufferedReader in = new BufferedReader(in2);) {
            return parse(in);
        }
    }