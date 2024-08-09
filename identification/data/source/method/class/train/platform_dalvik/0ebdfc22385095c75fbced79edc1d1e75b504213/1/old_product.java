public final void merge() {
            TableOfContents.Section aSection = getSection(dexA.getTableOfContents());
            TableOfContents.Section bSection = getSection(dexB.getTableOfContents());
            getSection(contentsOut).off = out.getPosition();

            DexBuffer.Section inA = aSection.exists() ? dexA.open(aSection.off) : null;
            DexBuffer.Section inB = bSection.exists() ? dexB.open(bSection.off) : null;
            int aOffset = -1;
            int bOffset = -1;
            int aIndex = 0;
            int bIndex = 0;
            int outCount = 0;
            T a = null;
            T b = null;

            while (true) {
                if (a == null && aIndex < aSection.size) {
                    aOffset = inA.getPosition();
                    a = read(inA, aIndexMap, aIndex);
                }
                if (b == null && bIndex < bSection.size) {
                    bOffset = inB.getPosition();
                    b = read(inB, bIndexMap, bIndex);
                }

                // Write the smaller of a and b. If they're equal, write only once
                boolean advanceA;
                boolean advanceB;
                if (a != null && b != null) {
                    int compare = a.compareTo(b);
                    advanceA = compare <= 0;
                    advanceB = compare >= 0;
                } else {
                    advanceA = (a != null);
                    advanceB = (b != null);
                }

                T toWrite = null;
                if (advanceA) {
                    toWrite = a;
                    updateIndex(aOffset, aIndexMap, aIndex++, outCount);
                    a = null;
                    aOffset = -1;
                }
                if (advanceB) {
                    toWrite = b;
                    updateIndex(bOffset, bIndexMap, bIndex++, outCount);
                    b = null;
                    bOffset = -1;
                }
                if (toWrite == null) {
                    break; // advanceA == false && advanceB == false
                }
                write(toWrite);
                outCount++;
            }

            getSection(contentsOut).size = outCount;
        }