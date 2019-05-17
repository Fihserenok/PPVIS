package model;

public class Student {
    private String name;
    private String surname;
    private String secondName;
    private String group;
    private int ill;
    private int other;
    private int without;
    private int total;
    

    public Student(String name, String surname, String secondName, String group) {
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.group = group;
        this.ill = ill;
        this.other = other;
        this.without = without;
        this.total = ill + other + without;
    }
    
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
    public String getSecondName() {
        return secondName;
    }

    public String getGroup() {
        return group;
    }
    
    public int getIll() {
        return ill;
    }
    
    public int getOther() {
        return other;
    }
    
    public int getWithout() {
        return without;
    }
    
    public int getTotal() {
        return total;
    }
}
