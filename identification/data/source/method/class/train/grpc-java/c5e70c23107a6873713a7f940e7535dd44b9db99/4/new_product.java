protected void inboundHeadersReceived(Metadata headers) {
      Preconditions.checkState(!statusReported, "Received headers on closed stream");
      statsTraceCtx.clientInboundHeaders();

      boolean compressedStream = false;
      String streamEncoding = headers.get(CONTENT_ENCODING_KEY);
      if (fullStreamDecompression && streamEncoding != null) {
        if (streamEncoding.equalsIgnoreCase("gzip")) {
          setFullStreamDecompressor(new GzipInflatingBuffer());
          compressedStream = true;
        } else if (!streamEncoding.equalsIgnoreCase("identity")) {
          deframeFailed(
              Status.INTERNAL
                  .withDescription(
                      String.format("Can't find full stream decompressor for %s", streamEncoding))
                  .asRuntimeException());
          return;
        }
      }

      String messageEncoding = headers.get(MESSAGE_ENCODING_KEY);
      if (messageEncoding != null) {
        Decompressor decompressor = decompressorRegistry.lookupDecompressor(messageEncoding);
        if (decompressor == null) {
          deframeFailed(
              Status.INTERNAL
                  .withDescription(String.format("Can't find decompressor for %s", messageEncoding))
                  .asRuntimeException());
          return;
        } else if (decompressor != Codec.Identity.NONE) {
          if (compressedStream) {
            deframeFailed(
                Status.INTERNAL
                    .withDescription(
                        String.format("Full stream and gRPC message encoding cannot both be set"))
                    .asRuntimeException());
            return;
          }
          setDecompressor(decompressor);
        }
      }

      listener().headersRead(headers);
    }