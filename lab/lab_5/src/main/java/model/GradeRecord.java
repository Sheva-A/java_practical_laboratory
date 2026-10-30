package model;

import exception.InvalidGradeException;

public final class GradeRecord {
    private final String name;
    private final String subject;
    private final int grade;

    // Об'єкт з некоректними даними створити неможливо
    public GradeRecord(String name, String subject, int grade) throws InvalidGradeException {
        if (name == null || name.isBlank()) {
            throw new InvalidGradeException("ПІБ порожнє");
        }
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("оцінка " + grade + " поза межами 0..100");
        }
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }

    // Розбір рядка формату ПІБ;предмет;оцінка
    public static GradeRecord parse(String line) throws InvalidGradeException {
        if (line.isBlank()) {
            throw new InvalidGradeException("порожній рядок");
        }
        String[] parts = line.split(";", -1);
        if (parts.length != 3) {
            throw new InvalidGradeException("очікувалось 3 поля, отримано " + parts.length);
        }
        String gradeText = parts[2].trim();
        int grade;
        try {
            grade = Integer.parseInt(gradeText);
        } catch (NumberFormatException e) {
            throw new InvalidGradeException("\"" + gradeText + "\" не є цілим числом", e);
        }
        return new GradeRecord(parts[0].trim(), parts[1].trim(), grade);
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public int getGrade() {
        return grade;
    }
}
