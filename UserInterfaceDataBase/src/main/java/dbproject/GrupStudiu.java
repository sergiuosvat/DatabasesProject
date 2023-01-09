package dbproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GrupStudiu {
    private final StringProperty data;
    private final StringProperty nrCrt;
    private final StringProperty max;
    private final StringProperty materie;

    public GrupStudiu()
    {
        data = new SimpleStringProperty("data");
        nrCrt = new SimpleStringProperty("nrCrt");
        max = new SimpleStringProperty("max");
        materie = new SimpleStringProperty("materie");
    }

    public StringProperty dataProperty(){
        return data;
    }

    public void setData(String data1)
    {
        this.data.set(data1);
    }
    public StringProperty nrCrtProperty(){
        return nrCrt;
    }
    public void setNrCrt(String nrCrt1)
    {
        this.nrCrt.set(nrCrt1);
    }
    public StringProperty maxProperty()
    {
        return max;
    }
    public void setMax(String max1)
    {
        this.max.set(max1);
    }
    public StringProperty materieProperty()
    {
        return materie;
    }
    public void setMaterie(String materie1)
    {
        this.materie.set(materie1);
    }


}
