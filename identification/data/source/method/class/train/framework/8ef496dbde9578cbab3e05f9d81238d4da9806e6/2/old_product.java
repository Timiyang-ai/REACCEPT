@Override
    protected void setup(VaadinRequest request) {

        Upload upload = new Upload();
        upload.setId("upload");
        upload.setImmediate(false);
        addComponent(upload);

        Upload immediateUpload = new Upload();
        immediateUpload.setId("immediateupload");
        immediateUpload.setImmediate(true);
        addComponent(immediateUpload);
    }