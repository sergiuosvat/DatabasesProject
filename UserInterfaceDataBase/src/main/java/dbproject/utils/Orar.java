package dbproject.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Orar {
    private final StringProperty durata;
    private final StringProperty activitate;
    private final StringProperty data;
    private final StringProperty materie;
    public Orar()
    {
        durata = new SimpleStringProperty(this, "student");
        activitate = new SimpleStringProperty(this, "activitate");
        data = new SimpleStringProperty(this,"data");
        materie = new SimpleStringProperty(this, "materie");
    }

    public StringProperty durataProperty()
    {
        return durata;
    }
    public StringProperty activitateProperty()
    {
        return activitate;
    }
    public StringProperty dataProperty()
    {
        return data;
    }
    public StringProperty materieProperty(){return materie;}
    public void setDurata(String durata)
    {
        this.durata.set(durata);
    }
    public void setActivitate(String activitate)
    {
        this.activitate.set(activitate);
    }
    public void setData(String data)
    {
        this.data.set(data);
    }
    public void setMaterie(String materie1) {this.materie.set(materie1);}
}


