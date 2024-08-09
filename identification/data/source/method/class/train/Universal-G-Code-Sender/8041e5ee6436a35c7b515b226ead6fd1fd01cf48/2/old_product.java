static public double parseCoord(List<String> argList, char c)
    {
        char address = Character.toUpperCase(c);
        for(String t : argList)
        {
            if (t.length() > 0 && Character.toUpperCase(t.charAt(0)) == address)
            {
                return Double.parseDouble(t.substring(1));
            }
        }
        return Double.NaN;
    }