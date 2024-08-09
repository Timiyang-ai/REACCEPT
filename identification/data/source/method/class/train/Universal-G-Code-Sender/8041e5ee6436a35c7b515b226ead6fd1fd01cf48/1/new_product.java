static public double parseCoord(List<String> argList, char c)
    {
        String word = extractWord(argList, c);
        if (word != null && word.length() > 1) {
            try {
                return Double.parseDouble(word.substring(1));
            } catch (NumberFormatException e) {
                return Double.NaN;
            }
        }
        return Double.NaN;
    }