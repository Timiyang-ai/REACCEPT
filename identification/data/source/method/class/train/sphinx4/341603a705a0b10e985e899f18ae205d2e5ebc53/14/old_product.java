public List<WordResult> align(URL audioUrl, String transcript)
            throws IOException, ProcessException {
        WordTokenizer tokenizer = new EnglishWordTokenizer();
        return align(audioUrl, tokenizer.getWords(transcript));
    }