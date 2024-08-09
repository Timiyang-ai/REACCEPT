public static String getUploadResultMessage(Cursor results, HashMap<String, String> result) {
        StringBuilder queryMessage = new StringBuilder();
        try {
            if (results != null && results.getCount() > 0) {
                results.moveToPosition(-1);
                while (results.moveToNext()) {
                    String name =
                            results.getString(
                                    results.getColumnIndex(InstanceProviderAPI.InstanceColumns.DISPLAY_NAME));
                    String id = results.getString(results.getColumnIndex(InstanceProviderAPI.InstanceColumns._ID));
                    String text = localizeDefaultAggregateSuccessfulText(result.get(id));
                    queryMessage
                            .append(name)
                            .append(" - ")
                            .append(text)
                            .append("\n\n");
                }
            }
        } finally {
            if (results != null) {
                results.close();
            }
        }
        return String.valueOf(queryMessage);
    }