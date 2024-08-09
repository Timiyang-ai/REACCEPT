public void setCaptionWithMarkers(
            Node tableCellNode,
            CharSequence captionOpen,
            CharSequence caption,
            CharSequence captionClose
    ) {
        setCaptionCell(new TableCell(tableCellNode, captionOpen, options.formatTableCaptionSpaces == DiscretionaryText.AS_IS ? caption : BasedSequence.of(caption).trim(), captionClose, 1, 1));
    }