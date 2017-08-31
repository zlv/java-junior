package com.acme.edu.logger.states;

import static java.lang.Long.max;
import static java.lang.Math.min;

/**
 * Created by Java_9 on 31.08.2017.
 */
public class AggregationStateUtils {

    public static long getSumInConstraints(long sum, long lowerBound, long upperBound) {
        return min(max(lowerBound, sum), upperBound);
    }

    public static long getRestInConstraints(long sum, long lowerBound, long upperBound) {
        long sumInConstraints = getSumInConstraints(sum, lowerBound, upperBound);
        return sum - sumInConstraints;
    }

}
