package model;

import java.util.HashMap;

public class Row {

    private HashMap<Integer, String> dataPair = new HashMap<>();

    public HashMap<Integer, String> getDataPair() {
        return dataPair;
    }

    public void setDataPair(HashMap<Integer, String> dataPair) {
        this.dataPair = dataPair;
    }
}
