public static void export(final Context context, final Data data)
      throws IOException {
    export(context.prop, data, data.meta.file.path());
  }