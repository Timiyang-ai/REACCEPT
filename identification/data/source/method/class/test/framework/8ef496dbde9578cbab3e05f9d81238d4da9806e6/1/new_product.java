@Override
    protected void setup(VaadinRequest request) {

        // by default is in immediate mode (since 8.0)
        Upload immediateUpload = new Upload();
        immediateUpload.setId("immediateupload");
        addComponent(immediateUpload);

        Upload upload = new Upload();
        upload.setId("upload");
        upload.setImmediateMode(false);
        addComponent(upload);
    }