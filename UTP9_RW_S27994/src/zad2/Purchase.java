/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad2;


import java.beans.*;

public class Purchase {
    private final String prod;
    private String data;
    private Double price;

    private final PropertyChangeSupport propertyChangeSupport;
    private final VetoableChangeSupport vetoableChangeSupport;

    public Purchase(String prod, String data, Double price) {
        this.prod = prod;
        this.data = data;
        this.price = price;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
        this.vetoableChangeSupport = new VetoableChangeSupport(this);
    }

    public void setData(String data) {
        String oldData = this.data;
        this.data = data;
        propertyChangeSupport.firePropertyChange("data", oldData, data);
    }

    public void setPrice(Double price) throws PropertyVetoException {
        Double oldPrice = this.price;
        vetoableChangeSupport.fireVetoableChange("price", oldPrice, price);
        this.price = price;
        propertyChangeSupport.firePropertyChange("price", oldPrice, price);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void addVetoableChangeListener(VetoableChangeListener listener) {
        vetoableChangeSupport.addVetoableChangeListener(listener);
    }

    @Override
    public String toString() {
        return "Purchase [prod=" + prod + ", data=" + data + ", price=" + price + "]";
    }
}
