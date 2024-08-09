static public double parseCoord(List<String> argList, char c)
    {
        char address = Character.toUpperCase(c);
        for(String t : argList)
        {
            if (t.length() > 1 && Character.toUpperCase(t.charAt(0)) == address)
            {
                try {
                    return Double.parseDouble(t.substring(1));
                } catch (NumberFormatException e) {
                    return Double.NaN;
                }
            }
        }
        return Double.NaN;
    }