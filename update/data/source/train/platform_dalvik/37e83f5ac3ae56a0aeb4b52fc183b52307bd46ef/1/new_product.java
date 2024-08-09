public String getEncoding() {
        if (encoder == null) {
            return null;
        }
        return HistoricalNamesUtil.getHistoricalName(encoder.charset().name());
    }