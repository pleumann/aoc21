package day19;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents a single probe with all its readings.
 */
class Probe {

    /**
     * The probe's number as in the input file.
     */
    int number;

    /**
     * The probe's readings, unmodified.
     */
    ArrayList<int[]> readings = new ArrayList<>();

    /**
     * The current applied rotation.
     */
    int[] rotation;

    /**
     * The currently applied translation.
     */
    int[] translation;

    /**
     * Creates a probe with the given name.
     */
    public Probe(int i) {
        number = i;
        setRotation(Matrix.ROT_VEC[0]);
    }

    /**
     * Adds a reading.
     */
    void add(int[] point) {
        readings.add(point);
    }
    
    /**
     * Returns the number of readings.
     */
    int size() {
        return readings.size();
    }

    /**
     * Sets the current rotation vector.
     */
    void setRotation(int [] vector) {
        rotation = vector;
    }

    /**
     * Sets the current translation vector.
     */
    void setTranslation(int[] offset) {
        translation = offset;
    }

    /**
     * Returns a single reading considering the currently applied rotation and
     * translation vectors.
     */
    int[] get(int index) {
        int[] p = readings.get(index);
        int[] q = new int[3];

        for (int i = 0; i < 3; i++) {
            int j = rotation[i];
            int s = j > 0 ? 1 : j < 0 ? -1 : 0;
            j = Math.abs(j);

            q[i] = s * p[j - 1];
        }

        if (translation != null) {
            for (int i = 0; i < 3; i++) {
                q[i] = q[i] + translation[i];
            }
        }

        return q;
    }

    /**
     * Returns a hash set of all rotated and translated readings.
     */
    HashSet<String> getAll() {
        HashSet<String> result = new HashSet<>();

        for (int i = 0; i < size(); i++) {
            result.add(Matrix.toString(get(i)));
        }

        return result;
    }

    @Override
    public String toString() {
        return "Scanner " + (number < 10 ? "0" : "") + number;
    }

}
