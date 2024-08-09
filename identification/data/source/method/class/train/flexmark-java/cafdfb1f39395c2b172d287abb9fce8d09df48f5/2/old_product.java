public void setCaptionWithMarkers(
            CharSequence captionOpen,
            CharSequence caption,
            CharSequence captionClose
    ) {
        setCaptionCell(new TableCell(captionOpen, options.formatTableCaptionSpaces == DiscretionaryText.AS_IS ? caption : BasedSequenceImpl.of(caption).trim(), captionClose, 1, 1));
    }