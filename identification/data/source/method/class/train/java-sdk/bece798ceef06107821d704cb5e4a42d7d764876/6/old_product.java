public Summary getSummary(final File imagesFile) {
        if (imagesFile == null || !imagesFile.exists())
            throw new IllegalArgumentException(
                    "imagesFile can not be null or empty");

        MultipartEntity reqEntity = new MultipartEntity();
        reqEntity.addPart(FILE, new FileBody(imagesFile));
        Request request = Request.Post(SUMMARY_PATH)
                .withEntity(reqEntity);

        return executeRequest(request, Summary.class);

    }