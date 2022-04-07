package com.cat.math3.methods;

import com.cat.math3.objects.Func;
import com.cat.math3.objects.MethodResult;

public class RectangleMethod {

    public static MethodResult rectangleMethod(Func function, double precision,
                                        double rangeBegin, double rangeEnd, double offset) {
        double currRes = 0;
        double prevRes = -precision * 2;
        int part = 4;

        while (part < 8192) {
            currRes = 0;

            double h = (rangeEnd - rangeBegin) / part;

            for (int i = 0; i < part; i++) {
                currRes += function.f(rangeBegin + h * (i + offset));
            }
            currRes *= h;

            if (Math.abs(currRes - prevRes) < precision) break;

            prevRes = currRes;
            part *= 2;
        }

        return new MethodResult(currRes, part/2);
    }

}
