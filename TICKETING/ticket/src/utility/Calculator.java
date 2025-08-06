package utility;

public class Calculator {
    public static double getProportionalValue(double refmin,double refMinValue,double refMax,double refMaxValue, double test){
        double distance = refMax - refmin;
        double distanceValue = refMaxValue - refMinValue;

        double distanceTest = test - refmin;

        return refMinValue + (distanceTest * distanceValue)/distance;
    }
}
