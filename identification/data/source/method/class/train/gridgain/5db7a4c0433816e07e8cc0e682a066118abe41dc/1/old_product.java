public void close() {
        for (int i = 0; i < results.length(); i++)
            closeResult(i);
    }