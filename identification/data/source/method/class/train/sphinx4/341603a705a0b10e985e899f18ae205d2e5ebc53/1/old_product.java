public List<WordResult> align(URL path, String text) throws IOException {
        recognizer.allocate();
        grammar.setText(text);
        context.setSpeechSource(path.openStream());
        context.processBatch();

        List<WordResult> result = recognizer.recognize().getWords();
        recognizer.deallocate();

        return result;
    }