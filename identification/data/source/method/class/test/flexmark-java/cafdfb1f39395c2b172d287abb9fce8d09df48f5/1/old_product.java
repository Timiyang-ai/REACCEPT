public void setCaptionWithMarkers(final CharSequence captionOpen, final CharSequence caption, final CharSequence captionClose) {
        this.captionOpen = BasedSequenceImpl.of(captionOpen);
        this.caption = BasedSequenceImpl.of(caption);
        this.captionClose = BasedSequenceImpl.of(captionClose);
    }