package dbproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Orar {
    private final StringProperty student;
    private final StringProperty activitate;
    private final StringProperty data;
    public Orar()
    {
        student = new SimpleStringProperty(this, "student");
        activitate = new SimpleStringProperty(this, "activitate");
        data = new SimpleStringProperty(this,"data");
    }

    public StringProperty studentProperty()
    {
        return student;
    }
    public StringProperty activitateProperty()
    {
        return activitate;
    }
    public StringProperty dataProperty()
    {
        return data;
    }
    public void setStudent(String student)
    {
        this.student.set(student);
    }
    public void setActivitate(String activitate)
    {
        this.activitate.set(activitate);
    }
    public void setData(String data)
    {
        this.data.set(data);
    }

    @Override
    public String toString() {
        return "Orar{" +
                "student=" + student +
                ", activitate=" + activitate +
                ", data=" + data +
                '}';
    }
}
