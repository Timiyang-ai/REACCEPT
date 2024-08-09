@Nullable
  public static TraceContextOrSamplingFlags parseB3SingleFormat(CharSequence b3, int beginIndex,
      int endIndex) {
    if (beginIndex == endIndex) {
      Platform.get().log("Invalid input: empty", null);
      return null;
    }

    int pos = beginIndex;
    if (pos + 1 == endIndex) { // possibly sampling flags
      return tryParseSamplingFlags(b3, pos);
    }

    // At this point we minimally expect a traceId-spanId pair
    if (endIndex < 16 + 1 + 16 /* traceid64-spanid */) {
      Platform.get().log("Invalid input: truncated", null);
      return null;
    } else if (endIndex > FORMAT_MAX_LENGTH) {
      Platform.get().log("Invalid input: too long", null);
      return null;
    }

    long traceIdHigh, traceId;
    if (b3.charAt(pos + 32) == '-') {
      traceIdHigh = tryParse16HexCharacters(b3, pos, endIndex);
      pos += 16; // upper 64 bits of the trace ID
      traceId = tryParse16HexCharacters(b3, pos, endIndex);
    } else {
      traceIdHigh = 0L;
      traceId = tryParse16HexCharacters(b3, pos, endIndex);
    }
    pos += 16; // traceId
    if (!checkHyphen(b3, pos++)) return null;

    if (traceIdHigh == 0L && traceId == 0L) {
      Platform.get().log("Invalid input: expected a 16 or 32 lower hex trace ID at offset 0", null);
      return null;
    }

    long spanId = tryParse16HexCharacters(b3, pos, endIndex);
    if (spanId == 0L) {
      Platform.get().log("Invalid input: expected a 16 lower hex span ID at offset {0}", pos, null);
      return null;
    }
    pos += 16; // spanid

    int flags = 0;
    long parentId = 0L;
    if (endIndex > pos) {
      // If we are at this point, we have more than just traceId-spanId.
      // If the sampling field is present, we'll have a delimiter 2 characters from now. Ex "-1"
      // If it is absent, but a parent ID is (which is strange), we'll have at least 17 characters.
      // Therefore, if we have less than two characters, the input is truncated.
      if (endIndex == pos + 1) {
        Platform.get().log("Invalid input: truncated", null);
        return null;
      }
      if (!checkHyphen(b3, pos++)) return null;

      // If our position is at the end of the string, or another delimiter is one character past our
      // position, try to read sampled status.
      if (endIndex == pos + 1 || delimiterFollowsPos(b3, pos, endIndex)) {
        flags = parseFlags(b3, pos);
        if (flags == 0) return null;
        pos++; // consume the sampled status
      }

      if (endIndex > pos) {
        // If we are at this point, we should have a parent ID, encoded as "-[0-9a-f]{16}"
        if (endIndex != pos + 17) {
          Platform.get().log("Invalid input: truncated", null);
          return null;
        }

        if (!checkHyphen(b3, pos++)) return null;
        parentId = tryParse16HexCharacters(b3, pos, endIndex);
        if (parentId == 0L) {
          Platform.get()
              .log("Invalid input: expected a 16 lower hex parent ID at offset {0}", pos, null);
          return null;
        }
      }
    }

    return TraceContextOrSamplingFlags.create(new TraceContext(
        flags,
        traceIdHigh,
        traceId,
        parentId,
        spanId,
        Collections.emptyList()
    ));
  }