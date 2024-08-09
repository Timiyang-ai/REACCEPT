public String asString() throws IOException {
        String res = IOs.readAsString(getReader());
        if (trim)
            return res.trim();
        else return res;
    }