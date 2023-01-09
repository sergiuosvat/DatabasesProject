package dbproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activitate2 {

    private final StringProperty tip;
    private final StringProperty data;
    private final StringProperty nrCrt;
    private final StringProperty durata;
    private final StringProperty frecventa;
    private final StringProperty materie;


    public Activitate2() {
        tip = new SimpleStringProperty(this, "tip");
        data = new SimpleStringProperty(this, "data");
        nrCrt = new SimpleStringProperty(this, "nrCrt");
        durata = new SimpleStringProperty(this, "durata");
        frecventa = new SimpleStringProperty(this, "frecventa");
        materie = new SimpleStringProperty(this, "idMaterie");
    }


    public StringProperty tipProperty() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip.set(tip);
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public StringProperty nrCrtProperty() {
        return nrCrt;
    }

    public void setNrCrt(String nrCrt) {
        this.nrCrt.set(nrCrt);
    }

    public StringProperty durataProperty() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata.set(durata);
    }

    public StringProperty frecventaProperty() {
        return frecventa;
    }

    public void setFrecventa(String frecventa) {
        this.frecventa.set(frecventa);
    }

    public StringProperty MaterieProperty() {
        return materie;
    }

    public void setMaterie(String idMaterie) {
        this.materie.set(idMaterie);
    }


}
