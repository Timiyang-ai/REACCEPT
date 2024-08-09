protected void inboundHeadersReceived(Metadata headers) {
      Preconditions.checkState(!statusReported, "Received headers on closed stream");
      statsTraceCtx.clientInboundHeaders();

      Decompressor decompressor = Codec.Identity.NONE;
      String encoding = headers.get(MESSAGE_ENCODING_KEY);
      if (encoding != null) {
        decompressor = decompressorRegistry.lookupDecompressor(encoding);
        if (decompressor == null) {
          deframeFailed(Status.INTERNAL.withDescription(
              String.format("Can't find decompressor for %s", encoding)).asRuntimeException());
          return;
        }
      }
      setDecompressor(decompressor);

      listener().headersRead(headers);
    }