public void print (PrintStream stream)
    {
        stream.print("[");

        boolean first = true;

        for (Map.Entry<K, Integer> entry : entrySet()) {
            stream.format(
                "%s%s:%d",
                first ? "" : " ",
                entry.getKey().toString(),
                entry.getValue());
            first = false;
        }

        stream.println("]");
    }