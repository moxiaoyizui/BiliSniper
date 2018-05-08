package com.yang.bilisniper.algorithm;

import io.shardingjdbc.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingjdbc.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
public class ModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> tableNames, PreciseShardingValue<Long> shardingValues) {
        for (String each : tableNames) {
            if (each.endsWith(shardingValues.getValue() % 11 + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
