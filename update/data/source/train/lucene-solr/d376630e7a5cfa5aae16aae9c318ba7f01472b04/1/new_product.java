private void add(CharsRef input, int numInputWords, CharsRef output, int numOutputWords, boolean includeOrig) {
      // first convert to UTF-8
      if (numInputWords <= 0) {
        throw new IllegalArgumentException("numInputWords must be > 0 (got " + numInputWords + ")");
      }
      if (input.length <= 0) {
        throw new IllegalArgumentException("input.length must be > 0 (got " + input.length + ")");
      }
      if (numOutputWords <= 0) {
        throw new IllegalArgumentException("numOutputWords must be > 0 (got " + numOutputWords + ")");
      }
      if (output.length <= 0) {
        throw new IllegalArgumentException("output.length must be > 0 (got " + output.length + ")");
      }

      assert !hasHoles(input): "input has holes: " + input;
      assert !hasHoles(output): "output has holes: " + output;

      //System.out.println("fmap.add input=" + input + " numInputWords=" + numInputWords + " output=" + output + " numOutputWords=" + numOutputWords);
      final int hashCode = UnicodeUtil.UTF16toUTF8WithHash(output.chars, output.offset, output.length, utf8Scratch);
      // lookup in hash
      int ord = words.add(utf8Scratch, hashCode);
      if (ord < 0) {
        // already exists in our hash
        ord = (-ord)-1;
        //System.out.println("  output=" + output + " old ord=" + ord);
      } else {
        //System.out.println("  output=" + output + " new ord=" + ord);
      }
      
      MapEntry e = workingSet.get(input);
      if (e == null) {
        e = new MapEntry();
        workingSet.put(CharsRef.deepCopyOf(input), e); // make a copy, since we will keep around in our map    
      }
      
      e.ords.add(ord);
      e.includeOrig |= includeOrig;
      maxHorizontalContext = Math.max(maxHorizontalContext, numInputWords);
      maxHorizontalContext = Math.max(maxHorizontalContext, numOutputWords);
    }