public void setCaptionWithMarkers(
            final CharSequence captionOpen,
            final CharSequence caption,
            final CharSequence captionClose
    ) {
        setCaptionCell(new TableCell(captionOpen, options.formatTableCaptionSpaces == DiscretionaryText.AS_IS ? caption : BasedSequenceImpl.of(caption).trim(), captionClose, 1, 1));
    }