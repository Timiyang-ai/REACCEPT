@Override
    protected void setup(VaadinRequest request) {

        // by default is not in immediate mode
        Upload upload = new Upload();
        upload.setId("upload");
        addComponent(upload);

        Upload immediateUpload = new Upload();
        immediateUpload.setId("immediateupload");
        immediateUpload.setImmediateMode(true);
        addComponent(immediateUpload);
    }