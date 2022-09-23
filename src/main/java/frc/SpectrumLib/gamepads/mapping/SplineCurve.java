// Created by Spectrum3847
// Based on Code from FRC# 2363
package frc.SpectrumLib.gamepads.mapping;

import java.util.Arrays;

/**
 * This class maps the value of a stick input to a spline curve. It is a subclass of <code>Curve
 * </code>.
 *
 * <p>For <code>LINEAR</code> curves, the points provided represent the vertecies of a linear spline
 * plotted on a graph (like a line graph).
 *
 * <p>For <code>SMOOTH</code> curves, the points provided represent the critical points on the graph
 * of the curve. The only exception to this is when <code>points.length</code> is equal to <code>2
 * </code> in which it will always be a linear spline between the two points.
 *
 * <p>For <code>RIGHT_STEP</code> or <code>LEFT_STEP</code> curves, the points provided represent
 * the points on a step function plotted on a graph. In <code>RIGHT_STEP</code> curves, the value on
 * the interval between points is determined by the value of the rightmost point in the interval. In
 * <code>LEFT_STEP</code> curves, the value on the interval between points is determined by the
 * value of the leftmost point in the interval.
 *
 * @author Justin Babilino
 * @version 0.0.3
 */
public class SplineCurve extends Curve {
    /** Types of spline segments used in spline curves. */
    private static enum SegmentType {
        LINEAR,
        RIGHT_PARABOLIC,
        LEFT_PARABOLIC,
        CUBIC,
        RIGHT_STEP,
        LEFT_STEP
    }

    /** Determines the type of spline that will pass through <code>points</code>. */
    private SplineType splineType;

    /** The set of points that the spline passes through. */
    private double[][] points;

    /**
     * Constructs an Spline Curve object which can be used to map a stick input based on a custom
     * curve profile. Initialized with values provided.
     *
     * @param splineType type of spline
     * @param points array of points used in spline
     * @param offset value used to offset the final curve
     * @param scalar value used to scale the value before offset
     * @param deadzone value for the width of the deadband in the center of the curve
     */
    public SplineCurve(
            SplineType splineType,
            double[][] points,
            double offset,
            double scalar,
            double deadzone) {
        setSplineType(splineType);
        setPoints(points);
        setOffset(offset);
        setScalar(scalar);
        setDeadzone(deadzone);
    }

    /**
     * Constructs an Spline Curve object which can be used to map a stick input based on a custom
     * curve profile. Initialized with default values: <code>
     *     splineType = SplineType.LINEAR;
     *     points = new double[][]{{-1.0, -1.0}, {0.0, 0.0}, {1.0, 1.0}};
     *     offset = 0.0;
     *     scalar = 1.0;
     *     deadzone = 0.0;
     * </code>
     */
    public SplineCurve() {
        setSplineType(SplineType.LINEAR);
        points =
                new double[][] {
                    {-1.0, -1.0}, {0.0, 0.0}, {1.0, 1.0}
                }; // have to do this to avoid exception handling
        setOffset(0.0);
        setScalar(1.0);
        setDeadzone(0.0);
    }

    /** @param input value to be mapped */
    @Override
    public double calculateMappedVal(double input) {
        double val = calculateOffset(calculateScalar(calculateSplineVal(calculateDeadzone(input))));
        if (val > 1.0) {
            val = 1.0;
        } else if (val < -1.0) {
            val = -1.0;
        }
        // val = (double) ((int) (val * 100000.0)) / 100000; // uncomment to round val
        return val;
    }

    /**
     * Maps the value of the input to the output using the spline curve provided
     *
     * @param input value to be mapped
     * @return mapped value
     */
    private double calculateSplineVal(double input) {
        double val = 0.0;
        if (points.length == 0) {
            val = 0.0;
        } else {
            if (input <= points[0][0]) {
                val = points[0][1];
            } else if (input >= points[points.length - 1][0]) {
                val = points[points.length - 1][1];
            } else {
                switch (splineType) {
                    case SMOOTH:
                        if (input <= points[1][0]) {
                            val =
                                    calculateSplineSegmentVal(
                                            input,
                                            SegmentType.LEFT_PARABOLIC,
                                            points[0],
                                            points[1]);
                        } else if (input >= points[points.length - 2][0]) {
                            val =
                                    calculateSplineSegmentVal(
                                            input,
                                            SegmentType.RIGHT_PARABOLIC,
                                            points[points.length - 2],
                                            points[points.length - 1]);
                        } else { // input is in central sections
                            boolean valFound = false;
                            int i = 1;
                            while (!valFound && i < points.length - 2) {
                                if (input <= points[i + 1][0]) {
                                    val =
                                            calculateSplineSegmentVal(
                                                    input,
                                                    SegmentType.CUBIC,
                                                    points[i],
                                                    points[i + 1]);
                                    valFound = true;
                                }
                                i++;
                            }
                        }
                        break;
                    default:
                        SegmentType segmentType = SegmentType.LINEAR;
                        switch (splineType) {
                            case LINEAR:
                                segmentType = SegmentType.LINEAR;
                                break;
                            case SMOOTH_AT_ENDS:
                                segmentType = SegmentType.CUBIC;
                                break;
                            case RIGHT_STEP:
                                segmentType = SegmentType.RIGHT_STEP;
                                break;
                            case LEFT_STEP:
                                segmentType = SegmentType.LEFT_STEP;
                                break;
                            default:
                                break;
                        }
                        boolean valFound = false;
                        int i = 0;
                        while (!valFound && i < points.length - 1) {
                            if (input <= points[i + 1][0]) {
                                val =
                                        calculateSplineSegmentVal(
                                                input, segmentType, points[i], points[i + 1]);
                                valFound = true;
                            }
                            i++;
                        }
                        break;
                }
            }
        }
        return val;
    }

    private double calculateSplineSegmentVal(
            double input, SegmentType segmentType, double[] pointA, double[] pointB) {
        switch (segmentType) {
            case LINEAR:
                return calculateLinearSplineVal(input, pointA, pointB);
            case RIGHT_PARABOLIC:
                return calculateParabolicSplineVal(input, pointA, pointB);
            case LEFT_PARABOLIC:
                return calculateParabolicSplineVal(
                        input, pointB, pointA); // hack - flip points to change side to left
            case CUBIC:
                return calculateCubicSplineVal(input, pointA, pointB);
            case RIGHT_STEP:
                return pointB[1]; // so simple this doesn't need a method
            case LEFT_STEP:
                return pointA[1];
            default:
                return 0.0;
        }
    }

    /**
     * Maps the value of the input to the output using a linear curve between the two points
     * provided.
     *
     * @param input value to be mapped
     * @param pointA point a
     * @param pointB point b
     * @return mapped value
     */
    private double calculateLinearSplineVal(double input, double[] pointA, double[] pointB) {
        return ((pointB[1] - pointA[1]) / (pointB[0] - pointA[0])) * (input - pointA[0])
                + pointA[1];
    }

    /**
     * Maps the value of the input to the output using the parabolic curve between the two points
     * provided.
     *
     * @param input value to be mapped
     * @param pointA point a
     * @param pointB point b
     * @return mapped value
     */
    private double calculateParabolicSplineVal(double input, double[] pointA, double[] pointB) {
        double transInput = input - pointA[0];
        double deltaX = pointB[0] - pointA[0];
        return ((pointB[1] - pointA[1]) / ((deltaX) * (deltaX))) * ((transInput) * (transInput))
                + pointA[1];
    }

    /**
     * Maps the value of the input to the output using the cubic curve between the two points
     * provided.
     *
     * @param input value to be mapped
     * @param pointA point a
     * @param pointB point b
     * @return mapped value
     */
    private double calculateCubicSplineVal(double input, double[] pointA, double[] pointB) {
        double transInput = input - pointA[0];
        double deltaX = pointB[0] - pointA[0];
        double deltaY = pointB[1] - pointA[1];
        return ((-2 * deltaY) / (deltaX * deltaX * deltaX)) * (transInput * transInput * transInput)
                + ((3 * deltaY) / (deltaX * deltaX)) * (transInput * transInput)
                + pointA[1];
    }

    /**
     * Returns a set of points of length <code>pointCount</code> on the curve.
     *
     * @param pointCount the amount of points on the curve
     * @return a 2D double array of points on the curve
     */
    public double[][] getCurvePoints(int pointCount) {
        double[][] points = new double[pointCount][2];
        double dx = 2.0 / (pointCount - 1);
        for (int i = 0; i < pointCount; i++) {
            double x = -1.0 + (i * dx);
            points[i][0] = x;
            points[i][1] = calculateMappedVal(x);
        }
        return points;
    }

    /** @param splineType the new value of <code>splineType</code> */
    public void setSplineType(SplineType splineType) {
        this.splineType = splineType;
    }

    /**
     * The values at each index <code>0</code> in each index of the point array MUST BE IN
     * INCREASING ORDER. There is no code in place to verify this. The array must be a X by 2 array,
     * where X is any valid array length.
     *
     * @param points the new value of <code>points</code>
     */
    public void setPoints(double[][] points) {
        this.points = Arrays.copyOf(points, points.length);
    }

    /** @return the current value of <code>splineType</code> */
    public SplineType getSplineType() {
        return splineType;
    }

    /** @return the current value of <code>points</code> */
    public double[][] getPoints() {
        return Arrays.copyOf(points, points.length);
    }
}
