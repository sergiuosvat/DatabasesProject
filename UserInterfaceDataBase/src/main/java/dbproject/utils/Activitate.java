package dbproject.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Activitate {
    private final StringProperty id;
    private final StringProperty tip;
    private final StringProperty data;
    private final StringProperty nrMin;
    private final StringProperty nrMax;
    private final StringProperty nrCrt;
    private final StringProperty durata;
    private final StringProperty frecventa;
    private final StringProperty idMaterie;
    private final StringProperty idProfesor;

    public Activitate() {
        id = new SimpleStringProperty(this, "id");
        tip = new SimpleStringProperty(this, "tip");
        data = new SimpleStringProperty(this, "data");
        nrMin = new SimpleStringProperty(this, "nrMin");
        nrMax = new SimpleStringProperty(this, "nrMax");
        nrCrt = new SimpleStringProperty(this, "nrCrt");
        durata = new SimpleStringProperty(this, "durata");
        frecventa = new SimpleStringProperty(this, "frecventa");
        idMaterie = new SimpleStringProperty(this, "idMaterie");
        idProfesor = new SimpleStringProperty(this, "idProfesor");
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
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

    public StringProperty nrMinProperty() {
        return nrMin;
    }

    public void setNrMin(String nrMin) {
        this.nrMin.set(nrMin);
    }

    public StringProperty nrMaxProperty() {
        return nrMax;
    }

    public void setNrMax(String nrMax) {
        this.nrMax.set(nrMax);
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

    public StringProperty idMaterieProperty() {
        return idMaterie;
    }

    public void setIdMaterie(String idMaterie) {
        this.idMaterie.set(idMaterie);
    }

    public StringProperty idProfesorProperty() {
        return idProfesor;
    }

    public void setIdProfesor(String idProfesor) {
        this.idProfesor.set(idProfesor);
    }

    @Override
    public String toString() {
        return "Activitate{" +
                "id=" + id +
                ", tip=" + tip +
                ", data=" + data +
                ", nrMin=" + nrMin +
                ", nrMax=" + nrMax +
                ", nrCrt=" + nrCrt +
                ", durata=" + durata +
                ", frecventa=" + frecventa +
                ", idMaterie=" + idMaterie +
                ", idProfesor=" + idProfesor +
                '}';
    }
}
