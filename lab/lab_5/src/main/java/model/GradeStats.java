package model;

// Підсумок рахується по одному запису, без збереження всіх записів у пам'яті
public class GradeStats {
    private int count;
    private int rejected;
    private long sum;
    private int min;
    private int max;

    public void add(GradeRecord record) {
        int grade = record.getGrade();
        if (count == 0) {
            min = grade;
            max = grade;
        }
        min = Math.min(min, grade);
        max = Math.max(max, grade);
        sum += grade;
        count++;
    }

    public void reject() {
        rejected++;
    }

    public int getCount() {
        return count;
    }

    public int getRejected() {
        return rejected;
    }

    public double getAverage() {
        return count == 0 ? 0 : (double) sum / count;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
