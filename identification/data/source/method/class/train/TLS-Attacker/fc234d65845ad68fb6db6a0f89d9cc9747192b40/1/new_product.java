public DtlsHandshakeMessageFragment buildCombinedFragment() {
        if (!isMessageComplete()) {
            LOGGER.warn("Returning incompletely received message! Missing pieces are replaced by 0 in content.");
        }
        if (isEmpty()) {
            throw new WorkflowExecutionException("The FragmentCollector is empty, cannot build combined fragment!");
        }

        DtlsHandshakeMessageFragment message = new DtlsHandshakeMessageFragment();
        message.setType(type);
        message.setLength(messageLength);
        message.setMessageSeq(messageSeq);
        message.setFragmentOffset(0);
        message.setFragmentLength(messageLength);
        message.setContent(getCombinedContent());
        DtlsHandshakeMessageFragmentSerializer serializer = new DtlsHandshakeMessageFragmentSerializer(message, null);
        message.setCompleteResultingMessage(serializer.serialize());
        return message;
    }