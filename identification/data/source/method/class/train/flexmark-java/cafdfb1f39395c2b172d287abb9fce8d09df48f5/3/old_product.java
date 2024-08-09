public void setCaptionWithMarkers(
            Node tableCellNode,
            CharSequence captionOpen,
            CharSequence caption,
            CharSequence captionClose
    ) {
        setCaptionCell(new TableCell(tableCellNode, captionOpen, options.formatTableCaptionSpaces == DiscretionaryText.AS_IS ? caption : BasedSequence.of(caption).subSequence(0, caption.length()).trim(), captionClose, 1, 1));
    }