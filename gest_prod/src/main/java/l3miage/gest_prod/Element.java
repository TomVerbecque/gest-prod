package l3miage.gest_prod;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Element {
    private SimpleStringProperty code;
    private SimpleStringProperty name;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty unit;

    public Element(String code, String name, int quantity, String unit) {
        this.code = new SimpleStringProperty(code);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.unit = new SimpleStringProperty(unit);
    }

    public String getCode() { return code.get(); }
    public String getName() { return name.get(); }
    public int getQuantity() { return quantity.get(); }
    public String getUnit() { return unit.get(); }

    // Plus les setters si nécessaire
    public void setCode(String code) { this.code.set(code); }
    public void setName(String name) { this.name.set(name); }
    public void setQuantity(int quantity) { this.quantity.set(quantity); }
    public void setUnit(String unit) { this.unit.set(unit); }
}
