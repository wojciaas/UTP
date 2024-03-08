/**
 *
 *  @author Reguła Wojciech S27994
 *
 */

package zad2;

public class Purchase {
    private final String clientId;
    private final String clientName;
    private final String clientSurname;
    private final String goodsName;
    private final double quantity;
    private final double costOfGoods;
    private final String allInfo;

    public Purchase(String allInfo) {
        this.allInfo = allInfo;
        String[] purchaseLine = allInfo.split(";");
        this.clientId = purchaseLine[0];
        this.clientSurname = purchaseLine[1].split("\\s")[0];
        this.clientName = purchaseLine[1].split("\\s")[1];
        this.goodsName = purchaseLine[2];
        this.costOfGoods = Double.parseDouble(String.valueOf(purchaseLine[3]));
        this.quantity = Double.parseDouble(String.valueOf(purchaseLine[4]));
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getCostOfGoods() {
        return costOfGoods;
    }

    public String getAllInfo() {
        return allInfo;
    }

    @Override
    public String toString() {
        return allInfo;
    }
}
