package com.example.project3apma;

import java.util.ArrayList;

public class Main {
    static double x_0 = 1000;
    static final int A = 24693; //multiplier
    static final int C = 3967; //increment 3517
    static final int K = (int) Math.pow(2, 18); //modulus
    static final int num_of_RV = 999999;
    static double[] W = new double[500];
    static int iter = -1;
    static double[] x = new double[1000000];
    static double[] u = new double[1000000];

    public static double randomNumberGenerator() {
        x[0] = ((A * x_0 + C) % K);
        u[0] = x[0] / (double) K;
        x_0 = x[0]; // update x_0 for the next iteration
        return u[0];
    }

    //X = Math.sqrt(-6498*Math.log(1-f))
    public static double calculateXusingCDF(double x) {
        double X = Math.sqrt(-6498 * Math.log(1 - x));
        return X;
    }


public static ArrayList<Double> generateSampleMeans(int n, int K) {
    ArrayList<Double> meanList = new ArrayList<Double>();
    double sum = 0;

    for(int j = 0; j < K; j++) {
        sum = 0;
        double[] CDFlist = new double[n];
        for (int i = 0; i < n; i++) {
            CDFlist[i] = calculateXusingCDF(randomNumberGenerator());
            sum+=CDFlist[i];
        }
        meanList.add(sum/n);
        sum = 0;
    }

    return meanList;
}
public static double meanOfSampleMeans(ArrayList<Double> sampleMeans){
        double sum = 0;
        for(int i = 0; i < sampleMeans.size(); i++){
            sum += sampleMeans.get(i);
        }
        return sum/sampleMeans.size();
}
//public static double varianceOfSampleMeans(ArrayList<Double> sampleMeans){
//        double meanSquared = Math.pow(meanOfSampleMeans(sampleMeans),2);
//        double sample = 0;
//        for(int i = 0; i < sampleMeans.size(); i++){
//            sample *= sample;
//            sample += sampleMeans.get(i);
//        }
//        double result = sample - meanSquared;
//        return result/sampleMeans.size();
//}
    public static double varianceOfSampleMeans(ArrayList<Double> sampleMeans){
        double meanSquared = Math.pow(meanOfSampleMeans(sampleMeans),2);
        double sumOfSquaredSamples = 0;
        for(int i = 0; i < sampleMeans.size(); i++){
            double sample = sampleMeans.get(i);
            sumOfSquaredSamples += sample * sample;
        }

        double result = sumOfSquaredSamples/sampleMeans.size();

        return result - meanSquared;

    }

    public static Double probabilityOfEvent(ArrayList<Double> sampleMeans, Double nkMean, Double nkStdDev, Double Zj){
        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < sampleMeans.size(); i++) {
            double result = (sampleMeans.get(i) - nkMean)/nkStdDev;
            if (Zj == -1.4) {
                if (result <= Zj) {
                    list.add(result);
                }
            }
            else if (Zj == -1.0) {
                if (result <= Zj) {
                    list.add(result);
                }
            }
            else if (Zj == -0.5) {
                if (result <= Zj) {
                    list.add(result);
                }
            }
            else if (Zj == 0.0) {
                if (result <= Zj) {
                    list.add(result);
                }
            }
            else if (Zj == 0.5) {
                if (result <= Zj) {
                    list.add(result);
                }
            }
            else if (Zj == 1.0) {
                if (result <= Zj) {
                    list.add(result);
                }
            }
            else if (Zj == 1.4) {
                if (result <= Zj) {
                    list.add(result);
                }
            }
        }
        Double probability = (double) list.size()/sampleMeans.size();
        return probability;
    }

    public static Double getMAD(Double probability, Double Zj) {
        Double MAD = 0.0;
        if (Zj == -1.4) {
            MAD = Math.abs(probability - 0.0808);
            return MAD;
        } else if (Zj == -1.0) {
            MAD = Math.abs(probability - 0.1587);
            return MAD;
        } else if (Zj == -0.5) {
            MAD = Math.abs(probability - 0.3085);
            return MAD;
        } else if (Zj == 0.0) {
            MAD = Math.abs(probability - 0.500);
            return MAD;
        } else if (Zj == 0.5) {
            MAD = Math.abs(probability - 0.6915);
            return MAD;
        } else if (Zj == 1.0) {
            MAD = Math.abs(probability - 0.8413);
            return MAD;
        } else if (Zj == 1.4) {
            MAD = Math.abs(probability - 0.9192);
            return MAD;
        }
        return MAD;
    }

//    double result = sumOfSquaredSamples - meanSquared;
//
//        return result/sampleMeans.size();
    public static void main(String[] args) {
        //real mean = 71.438

        //part two vals
        ArrayList<Double> ten = generateSampleMeans(10,110);
        ArrayList<Double> thirty = generateSampleMeans(30,110);
        ArrayList<Double> fifty = generateSampleMeans(50,110);
        ArrayList<Double> hundred = generateSampleMeans(100,110);
        ArrayList<Double> twofifty = generateSampleMeans(250,110);
        ArrayList<Double> fivehundred = generateSampleMeans(500,110);
        ArrayList<Double> thousand = generateSampleMeans(1000,110);

        //part three vals
        ArrayList<Double> threeFive = generateSampleMeans(3,5);
        double threeFiveMean = meanOfSampleMeans(threeFive);
        double threeFiveVariance = varianceOfSampleMeans(threeFive);

        ArrayList<Double> nineTwentyFive = generateSampleMeans(9,25);
        double nineTwentyFiveMean = meanOfSampleMeans(nineTwentyFive);
        double nineTwentyFiveVariance = varianceOfSampleMeans(nineTwentyFive);

        ArrayList<Double> twentySeven110 = generateSampleMeans(27,110);
        double twentySeven110Mean = meanOfSampleMeans(twentySeven110);
        double twentySeven110Variance = varianceOfSampleMeans(twentySeven110);

        ArrayList<Double> EightyOne550 = generateSampleMeans(81,550);
        double EightyOne550Mean = meanOfSampleMeans(EightyOne550);
        double EightyOne550Variance = varianceOfSampleMeans(EightyOne550);

//        System.out.println(probabilityOfEvent(EightyOne550, EightyOne550Mean, Math.sqrt(EightyOne550Variance), -1.4));
//        System.out.println(probabilityOfEvent(EightyOne550, EightyOne550Mean, Math.sqrt(EightyOne550Variance), -1.0));
//        System.out.println(probabilityOfEvent(EightyOne550, EightyOne550Mean, Math.sqrt(EightyOne550Variance), -0.5));
//        System.out.println(probabilityOfEvent(EightyOne550, EightyOne550Mean, Math.sqrt(EightyOne550Variance), 0.0));
//        System.out.println(probabilityOfEvent(EightyOne550, EightyOne550Mean, Math.sqrt(EightyOne550Variance), 0.5));
//        System.out.println(probabilityOfEvent(EightyOne550, EightyOne550Mean, Math.sqrt(EightyOne550Variance), 1.0));
//        System.out.println(probabilityOfEvent(EightyOne550, EightyOne550Mean, Math.sqrt(EightyOne550Variance), 1.4));
        System.out.println(getMAD(0.089090909, -1.4));
        System.out.println(getMAD(0.17090909, -1.0));
        System.out.println(getMAD(0.4, -0.5));
        System.out.println(getMAD(0.48, 0.0));
        System.out.println(getMAD(0.6763636, 0.5));
        System.out.println(getMAD(0.80, 1.0));
        System.out.println(getMAD(0.92, 1.4));

        //testing

//        System.out.println(Math.sqrt(threeFiveVariance));
//        System.out.println(Math.sqrt(nineTwentyFiveVariance));
//        System.out.println(Math.sqrt(twentySeven110Variance));
//        System.out.println(Math.sqrt(EightyOne550Variance));


    }
}

