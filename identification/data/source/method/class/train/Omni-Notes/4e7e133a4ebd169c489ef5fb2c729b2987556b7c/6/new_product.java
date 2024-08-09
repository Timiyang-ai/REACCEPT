public static Bitmap getBitmapFromAttachment(Context mContext, Attachment mAttachment, int width, int height) {
        Bitmap bmp = null;
        String path;
        mAttachment.getUri().getPath();

		// Video or image
		if (AttachmentsHelper.typeOf(mAttachment, Constants.MIME_TYPE_VIDEO, Constants.MIME_TYPE_IMAGE, Constants.MIME_TYPE_SKETCH)) {
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

			// Audio
        } else if (Constants.MIME_TYPE_AUDIO.equals(mAttachment.getMime_type())) {
            bmp = ThumbnailUtils.extractThumbnail(
                    BitmapUtils.decodeSampledBitmapFromResourceMemOpt(mContext.getResources().openRawResource(R
									.raw.play), width, height), width, height);

		// File
		} else if (Constants.MIME_TYPE_FILES.equals(mAttachment.getMime_type())) {

			// vCard
			if (Constants.MIME_TYPE_CONTACT_EXT.equals(FilenameUtils.getExtension(mAttachment.getName()))) {
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