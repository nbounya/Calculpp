package com.nassim.calculpp;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CalculationMethods {
    static DecimalFormat form = new DecimalFormat("0.000");

    static String[] addTime(int day1, int hour1, int min1, double sec1, int day2, int hour2, int min2, double sec2){
        int day3, hour3, min3;
        double sec3;

        sec3 = sec1 + sec2;

        if(sec3>=60){
            min1++;
            sec3 = sec3 - 60;
        }

        min3 = min1 + min2;

        if(min3>=60){
            hour1++;
            min3 = min3 - 60;
        }

        hour3 = hour1 + hour2;

        if(hour3>=24){
            day1++;
            hour3 = hour3 - 24;
        }

        day3 = day1 + day2;

        String[] result = {form.format(day3), form.format(hour3), form.format(min3), form.format(sec3)};

        return result;
    }

    static long calculateDaysBetweenDates(Date startDate, Date endDate){
        long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
        long daysBetween = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return daysBetween;
    }

    static Date addSubtractDate(Date startDate, int years, int months, int weeks, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DAY_OF_YEAR, days);
        cal.add(Calendar.DAY_OF_YEAR, days*7);
        cal.add(Calendar.MONTH, months);
        cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }

    static double calculateCubeVolume(double length){
        double volume = length * length * length;
        return volume;
    }

    static double calculateCuboidVolume(double length1, double length2, double length3){
        double volume = length1 * length2 * length3;
        return volume;
    }

    static double calculateSphereVolume(double radius){
        double volume = 1.3333333333 * Math.PI * Math.pow(radius, 3);
        return volume;
    }

    static double calculatePyramidVolume(double width, double length, double height){
        double volume = (length * width * height) / 3;
        return volume;
    }

    static double calculateCylinderVolume(double radius, double height){
        double volume = Math.PI * Math.pow(radius, 2) * height;
        return volume;
    }

    static double calculateConeVolume(double radius, double height){
        double volume = Math.PI * Math.pow(radius, 2) * (height / 3);
        return volume;
    }

    static double calculateBMI(double weight, double height, boolean isMetric){
        double bmi;
        if(isMetric){
            bmi = weight/(height*height);
        } else {
            bmi = (weight/(height*height))*703;
        }

        return bmi;
    }

    static double[] calculateIdealWeight(double height, boolean isMetric){
        double[] weight = {0, 0};
        if(isMetric){
            weight[0] = 18.5 * height * height;
            weight[1] = 25 * height * height;
        } else {
            weight[0] = (18.5 * height * height) / 703;
            weight[1] = (25 * height * height) / 703;
        }

        return weight;
    }

    static int calculateCount(double[] data){
        int count = 0;
        for (int i = 0; i < data.length; i ++) {
            count ++;
        }
        return count;
    }

    static double calculateSum(double[] data){
        double sum = 0;
        for (int i = 0; i < data.length; i ++) {
            sum = sum + data[i];
        }
        return sum;
    }

    static double calculateAverage(double[] data){
        double average;
        average = calculateSum(data) / data.length;
        return average;
    }

    static double calculateMedian(double[] data){
        Arrays.sort(data);
        double median;

        if (data.length % 2 == 0)
            median = ((double)data[data.length/2] + (double)data[data.length/2 - 1])/2;
        else
            median = (double) data[data.length/2];

        return median;
    }

    static double calculateLargest(double[] data){
        double largest = data[0];
        for (int i = 0; i < data.length; i ++) {
            if(data[i] > largest){
                largest = data[i];
            }
        }
        return largest;
    }

    static double calculateSmallest(double[] data){
        double smallest = data[0];
        for (int i = 0; i < data.length; i ++) {
            if(data[i] < smallest){
                smallest = data[i];
            }
        }
        return smallest;
    }

    static double calculateRange(double[] data){
        double range;
        range = calculateLargest(data) - calculateSmallest(data);
        return range;
    }

    static double calculateGeometricMean(double[] data){
        double mean;
        double product = 1;
        for (int i = 0; i < data.length; i ++) {
            product = product * data[i];
        }

        mean = Math.pow(Math.E, Math.log(product)/calculateCount(data));
        return mean;
    }

    static double calculatePopulationStandardDeviation(double[] data){
        double sd = 0;

        for (int i = 0; i < data.length; i++)
        {
            sd += (data[i] - calculateAverage(data)) * (data[i] - calculateAverage(data)) / data.length;
        }

        return Math.sqrt(sd);
    }

    static double calculateSampleStandardDeviation(double[] data){
        double sd = 0;

        for (int i = 0; i < data.length; i++)
        {
            sd += (data[i] - calculateAverage(data)) * (data[i] - calculateAverage(data)) / (data.length - 1);
        }

        return Math.sqrt(sd);
    }

    static double calculateVariance(double[] data){
        double variance = 0.0;
        double squares = 1;
        double sum = calculateSum(data);
        double count = calculateCount(data);

        for (int i = 0; i < data.length; i++)
        {
            squares = data[i]*data[i] + squares;
        }

        if (calculateCount(data) > 1) {
            variance = (squares-(double)sum*sum/count)/(count-1);
        }
        return variance;
    }

    static double calculateSampleVariance(double[] data) {
        double sumDiffsSquared=0.0;
        double avg = calculateAverage(data);
        for (double value : data)
        {
            double diff=value-avg;
            diff*=diff;
            sumDiffsSquared+=diff;
        }
        return sumDiffsSquared/(data.length-1);
    }

    static int calculatePermutation(int n, int r){
        int result, factorn = 1, factornr = 1;
        if(n==0){
            factorn = 1;
        } else{
            for(int i = 1; i <= n; i++){
                factorn = factorn*i;
            }
        }

        if((n-r)==0){
            factornr = 1;
        } else{
            for(int i = 1; i <= n-r; i++){
                factornr *= i;
            }
        }
        result = factorn / factornr;
        return result;
    }
    static int calculateCombination(int n, int r) {
        int result, factorn = 1, factornr = 1, factorr = 1;
        if (n == 0) {
            factorn = 1;
        } else {
            for (int i = 1; i <= n; i++) {
                factorn = factorn * i;
            }
        }

        if ((n-r) == 0) {
            factornr = 1;
        } else {
            for (int i = 1; i <= (n-r); i++) {
                factornr = factornr * i;
            }
        }

        if (r == 0) {
            factorr = 1;
        } else {
            for (int i = 1; i <= r; i++) {
                factorr = factorr * i;
            }
        }


        result = factorn / (factorr * factornr);
        return result;
    }
}
