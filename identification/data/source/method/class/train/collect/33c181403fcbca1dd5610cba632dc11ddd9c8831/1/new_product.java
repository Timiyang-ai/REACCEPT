public static String getUploadResultMessage(Context context, Map<String, String> result) {
        Set<String> keys = result.keySet();
        Iterator<String> it = keys.iterator();

        StringBuilder message = new StringBuilder();
        int count = keys.size();
        while (count > 0) {
            String[] selectionArgs;

            if (count > ApplicationConstants.SQLITE_MAX_VARIABLE_NUMBER) {
                selectionArgs = new String[ApplicationConstants.SQLITE_MAX_VARIABLE_NUMBER];
            } else {
                selectionArgs = new String[count];
            }

            StringBuilder selection = new StringBuilder();
            selection.append(InstanceProviderAPI.InstanceColumns._ID + " IN (");

            int i = 0;
            while (it.hasNext() && i < selectionArgs.length) {
                selectionArgs[i] = it.next();
                selection.append('?');

                if (i != selectionArgs.length - 1) {
                    selection.append(',');
                }
                i++;
            }

            selection.append(')');
            count -= selectionArgs.length;

            message.append(InstanceUploaderUtils
                    .getUploadResultMessageForInstances(new InstancesDao().getInstancesCursor(selection.toString(), selectionArgs), result));
        }

        if (message.length() == 0) {
            message = new StringBuilder(context.getString(R.string.no_forms_uploaded));
        }

        return message.toString().trim();
    }