public static String getUploadResultMessage(Cursor instancesProcessed,
                                                HashMap<String, String> resultMessagesByInstanceId) {
        StringBuilder queryMessage = new StringBuilder();
        try {
            if (instancesProcessed != null && instancesProcessed.getCount() > 0) {
                instancesProcessed.moveToPosition(-1);
                while (instancesProcessed.moveToNext()) {
                    String name =
                            instancesProcessed.getString(
                                    instancesProcessed.getColumnIndex(InstanceProviderAPI.InstanceColumns.DISPLAY_NAME));
                    String id = instancesProcessed.getString(instancesProcessed.getColumnIndex(InstanceProviderAPI.InstanceColumns._ID));
                    String text = localizeDefaultAggregateSuccessfulText(resultMessagesByInstanceId.get(id));
                    queryMessage
                            .append(name)
                            .append(" - ")
                            .append(text)
                            .append("\n\n");
                }
            }
        } finally {
            if (instancesProcessed != null) {
                instancesProcessed.close();
            }
        }
        return String.valueOf(queryMessage);
    }