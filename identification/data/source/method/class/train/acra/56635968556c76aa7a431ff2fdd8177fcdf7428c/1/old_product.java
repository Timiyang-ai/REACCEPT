@NonNull
    @Override
    public List<Uri> getAttachments(Context context, CoreConfiguration configuration) {
        final ArrayList<Uri> result = new ArrayList<>();
        for (String s : configuration.attachmentUris()){
            try {
                result.add(Uri.parse(s));
            }catch (Exception e){
                ACRA.log.e(LOG_TAG, "Failed to parse Uri " + s, e);
            }
        }
        return result;
    }