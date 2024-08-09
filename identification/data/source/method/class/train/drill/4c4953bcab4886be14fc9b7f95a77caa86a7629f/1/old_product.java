@Override
  public byte getByte( int rowOffset ) throws InvalidAccessException {
    final byte result;
    switch ( getType().getMinorType() ) {
      // 1. Regular type:
      case TINYINT:
        result = innerAccessor.getByte( rowOffset );
        break;

      // 2. Converted-from types:
      case SMALLINT:
        result = getByteValueOrThrow( innerAccessor.getShort( rowOffset ),
                                      "Java short / SQL SMALLINT" );
        break;
      case INT:
        result = getByteValueOrThrow( innerAccessor.getInt( rowOffset ),
                                      "Java int / SQL INTEGER" );
        break;
      case BIGINT:
        result = getByteValueOrThrow( innerAccessor.getLong( rowOffset ),
                                      "Java long / SQL BIGINT" );
        break;
      case FLOAT4:
        result = getByteValueOrThrow( innerAccessor.getFloat( rowOffset ),
                                      "Java float / SQL REAL/FLOAT" );
        break;
      case FLOAT8:
        result = getByteValueOrThrow( innerAccessor.getDouble( rowOffset ),
                                      "Java double / SQL DOUBLE PRECISION" );
        break;

      // 3. Not-yet-converted and unconvertible types:
      default:
        result = innerAccessor.getByte( rowOffset );
        break;
    }
    return result;
  }