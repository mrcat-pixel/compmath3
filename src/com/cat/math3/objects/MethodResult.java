package com.cat.math3.objects;

public class MethodResult {

    private double value;
    private int partitionNumber;

    public double getValue() {
        return value;
    }

    public int getPartitionNumber() {
        return partitionNumber;
    }

    public MethodResult(double value, int partitionNumber) {
        this.value = value;
        this.partitionNumber = partitionNumber;
    }

    public String toString() {
        return "The result is: " + value + "; \n Number of partitions: " + partitionNumber;
    }
}
