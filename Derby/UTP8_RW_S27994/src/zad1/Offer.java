package zad1;

import java.util.Date;
import java.util.Locale;

public class Offer {
    private final Locale LOCALE;
    private final String COUNTRY;
    private final Date DEPARTURE_DATE;
    private final Date RETURN_DATE;
    private final String PLACE;
    private final Double PRICE;
    private final String CURRENCY;

    public Offer(Locale LOCALE, String COUNTRY, Date DEPARTURE_DATE, Date RETURN_DATE, String PLACE, Double PRICE, String CURRENCY) {
        this.LOCALE = LOCALE;
        this.COUNTRY = COUNTRY;
        this.DEPARTURE_DATE = DEPARTURE_DATE;
        this.RETURN_DATE = RETURN_DATE;
        this.PLACE = PLACE;
        this.PRICE = PRICE;
        this.CURRENCY = CURRENCY;
    }

    public Locale getLocale() {
        return LOCALE;
    }

    public String getCountry() {
        return COUNTRY;
    }

    public Date getDepartureDate() {
        return DEPARTURE_DATE;
    }

    public Date getReturnDate() {
        return RETURN_DATE;
    }

    public String getPlace() {
        return PLACE;
    }

    public Double getPrice() {
        return PRICE;
    }

    public String getCurrency() {
        return CURRENCY;
    }
}
