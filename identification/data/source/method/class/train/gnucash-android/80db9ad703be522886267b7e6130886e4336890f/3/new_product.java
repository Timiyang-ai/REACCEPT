static void backupAllBooks() {
        BooksDbAdapter booksDbAdapter = BooksDbAdapter.getInstance();
        List<String> bookUIDs = booksDbAdapter.getAllBookUIDs();
        Context context = GnuCashApplication.getAppContext();

        for (String bookUID : bookUIDs) {
            String backupFile = getBookBackupFileUri(bookUID);
            if (backupFile == null){
                backupBook(bookUID);
                continue;
            }

            try (BufferedOutputStream bufferedOutputStream =
                    new BufferedOutputStream(context.getContentResolver().openOutputStream(Uri.parse(backupFile)))){
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(bufferedOutputStream);
                OutputStreamWriter writer = new OutputStreamWriter(gzipOutputStream);
                ExportParams params = new ExportParams(ExportFormat.XML);
                new GncXmlExporter(params).generateExport(writer);
                writer.close();
            } catch (IOException ex) {
                Log.e(LOG_TAG, "Auto backup failed for book " + bookUID);
                ex.printStackTrace();
                Crashlytics.logException(ex);
            }
        }
    }