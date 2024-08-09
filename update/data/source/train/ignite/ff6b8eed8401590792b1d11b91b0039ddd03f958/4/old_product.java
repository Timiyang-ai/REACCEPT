public default C build(Stream<UpstreamEntry<K, V>> upstreamData, long upstreamDataSize) {
        return build(upstreamData.iterator(), upstreamDataSize);
    }