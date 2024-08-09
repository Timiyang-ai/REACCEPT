public String getEncoding() {
        if (encoder == null) {
            return null;
        }
        return InputStreamReader.HistoricalNamesUtil.getHistoricalName(encoder
                .charset().name());
    }