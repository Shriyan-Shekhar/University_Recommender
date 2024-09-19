package comp3111.qsproject;

import java.lang.reflect.Field;

/**
 * One QS info item
 */

public class QSItem {
    public String name;
    public String year;
    public String rank;
    public String score;
    public String country;
    public String city;
    public String region;
    public String type;
    public String researchOutput;
    public String studentFacultyRatio;
    public String internationalStudents;
    public String size;
    public String facultyCount;
    public String link;
    QSItem(String[] string_line) {
        assert(string_line.length == 15);
        name = string_line[0];
        year = string_line[1];
        rank = string_line[2];
        score = string_line[3];
        link = string_line[4];
        country = string_line[5];
        city = string_line[6];
        region = string_line[7];
        type = string_line[9];
        researchOutput = string_line[10];
        studentFacultyRatio = string_line[11];
        internationalStudents = string_line[12];
        size = string_line[13];
        facultyCount = string_line[14];
    }

    public String getRank() {
        return rank;
    }

    public String getName() { return name; }

    public String getYear (){ return year; }

    public String getRegion (){ return region; }
    public String getScore() { return score; }

    public String getCountry() { return country; }

    public String getCity() { return city; }

    public String getType() { return type; }

    public String getResearchOutput() { return researchOutput; }

    public String getStudentFacultyRatio() {return  studentFacultyRatio; }

    public String getInternationalStudents() {
        return internationalStudents;
    }

    public String getFacultyCount() {
        return facultyCount;
    }

    public String getLink() {return link;}

    /**
     * Retrieves the value of the specified property using reflection.
     * @author Shriyan Shekhar
     * @param property The name of the property to retrieve.
     * @return The value of the property, or null if the property doesn't exist or is inaccessible.
     */
    String getProperty(String property) {
        String propertyValue = new String();
        Field field;

        try {
            field = getClass().getDeclaredField(property);
            field.setAccessible(true);
            Object value = field.get(this);
            if (value != null){
                propertyValue = value.toString();
            }
            else{
                propertyValue = null;
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            field = null;
        }

        if (field == null) {
            return null;
        }

        return propertyValue;
    }
}
