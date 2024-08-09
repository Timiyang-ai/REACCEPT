public static Bitmap getBitmapFromAttachment(Context mContext, Attachment mAttachment, int width, int height) {
        Bitmap bmp = null;
        String path;
        mAttachment.getUri().getPath();

        // Video
        if (Constants.MIME_TYPE_VIDEO.equals(mAttachment.getMime_type())) {
            // Tries to retrieve full path from ContentResolver if is a new video
            path = StorageHelper.getRealPathFromURI(mContext, mAttachment.getUri());
            // .. or directly from local directory otherwise
            if (path == null) {
                path = FileHelper.getPath(mContext, mAttachment.getUri());
            }
            bmp = ThumbnailUtils.createVideoThumbnail(path, Thumbnails.MINI_KIND);
            if (bmp == null) {
                return null;
            } else {
                bmp = BitmapUtils.createVideoThumbnail(mContext, bmp, width, height);
            }

		// Image
        } else if (Constants.MIME_TYPE_IMAGE.equals(mAttachment.getMime_type())
                || Constants.MIME_TYPE_SKETCH.equals(mAttachment.getMime_type())) {
            try {
				RequestOptions options = new RequestOptions()
						.centerCrop()
						.error(R.drawable.attachment_broken);
				bmp = Glide.with(OmniNotes.getAppContext()).asBitmap()
						.apply(options)
						.load(mAttachment.getUri())
						.submit(width, height)
						.get();
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