public static Bitmap getBitmapFromAttachment (Context mContext, Attachment mAttachment, int width, int height) {
    Bitmap bmp = null;
    mAttachment.getUri().getPath();

    if (AttachmentsHelper.typeOf(mAttachment, MIME_TYPE_VIDEO, MIME_TYPE_IMAGE, MIME_TYPE_SKETCH)) {
      try {
        bmp = Glide.with(OmniNotes.getAppContext()).asBitmap()
                   .apply(new RequestOptions()
                       .centerCrop()
                       .error(R.drawable.attachment_broken))
                   .load(mAttachment.getUri())
                   .submit(width, height).get();
      } catch (NullPointerException | InterruptedException | ExecutionException e) {
        bmp = null;
      }

    } else if (MIME_TYPE_AUDIO.equals(mAttachment.getMime_type())) {
      bmp = ThumbnailUtils.extractThumbnail(
          BitmapUtils.decodeSampledBitmapFromResourceMemOpt(mContext.getResources().openRawResource(R
              .raw.play), width, height), width, height);

    } else if (MIME_TYPE_FILES.equals(mAttachment.getMime_type())) {
      if (MIME_TYPE_CONTACT_EXT.equals(FilenameUtils.getExtension(mAttachment.getName()))) {
        bmp = ThumbnailUtils.extractThumbnail(
            BitmapUtils.decodeSampledBitmapFromResourceMemOpt(mContext.getResources().openRawResource(R
                .raw.vcard), width, height), width, height);
      } else {
        bmp = ThumbnailUtils.extractThumbnail(
            BitmapUtils.decodeSampledBitmapFromResourceMemOpt(mContext.getResources().openRawResource(R
                .raw.files), width, height), width, height);
      }
    }

    return bmp;
  }