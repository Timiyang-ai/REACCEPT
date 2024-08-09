public List<WordResult> align(URL audioUrl, String transcript)
            throws IOException {
        return align(audioUrl, getWordExpander().expand(transcript));
    }