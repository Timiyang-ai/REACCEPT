public default C build(LearningEnvironment env, Stream<UpstreamEntry<K, V>> upstreamData, long upstreamDataSize) {
        return build(env, upstreamData.iterator(), upstreamDataSize);
    }