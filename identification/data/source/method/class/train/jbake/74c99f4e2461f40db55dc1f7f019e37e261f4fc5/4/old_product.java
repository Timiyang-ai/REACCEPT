public void crawl(File path) {
        File[] contents = path.listFiles(FileUtil.getFileFilter());
        if (contents != null) {
            Arrays.sort(contents);
            for (File sourceFile : contents) {
                if (sourceFile.isFile()) {
                    System.out.print("Processing [" + sourceFile.getPath() + "]... ");
                    String sha1 = buildHash(sourceFile);
                    String uri = buildURI(sourceFile);
                    boolean process = true;
                    DocumentStatus status = DocumentStatus.NEW;
                    for (String docType : DocumentTypes.getDocumentTypes()) {
                        status = findDocumentStatus(docType, uri, sha1);
                        switch (status) {
                            case UPDATED:
                                System.out.print(" : modified ");
                                DBUtil.update(db, "delete from " + docType + " where uri=?", uri);
                                break;
                            case IDENTICAL:
                                System.out.print(" : same ");
                                process = false;
                        }
                        if (!process) {
                            break;
                        }
                    }
                    if (DocumentStatus.NEW == status) {
                        System.out.print(" : new ");
                    }
                    if (process) { // new or updated
                        crawlSourceFile(sourceFile, sha1, uri);
                    }
                }
                if (sourceFile.isDirectory()) {
                    crawl(sourceFile);
                } else {
                    System.out.println("done!");
                }
            }
        }
    }