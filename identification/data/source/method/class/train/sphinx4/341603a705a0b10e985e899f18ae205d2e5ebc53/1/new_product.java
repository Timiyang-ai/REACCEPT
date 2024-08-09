public List<WordResult> align(URL audioUrl, String transcript)
            throws IOException {
        WordTokenizer tokenizer = new EnglishWordTokenizer();
        return align(audioUrl, tokenizer.getWords(transcript));
    }