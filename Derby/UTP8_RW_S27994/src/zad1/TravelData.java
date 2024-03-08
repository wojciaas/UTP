package zad1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.*;
import java.util.*;

public class TravelData {
    private final File DATA_DIR;
    private final List<Offer> OFFERS;
    private String dateFormat;

    public TravelData(File DATA_DIR) {
        this.DATA_DIR = DATA_DIR;
        OFFERS = new ArrayList<>();
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        List<String> offers = new ArrayList<>();
        this.dateFormat = dateFormat;
        Locale loc = new Locale(locale.split("_")[0], locale.split("_")[1]);
        if (this.OFFERS.isEmpty()) {
            TreeSet<File> files = new TreeSet<>(Arrays.asList(Objects.requireNonNull(DATA_DIR.listFiles())));
            files.forEach(file -> {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] fields = line.split("\t");
                        Locale contractorLoc = Locale.forLanguageTag(fields[0].replace("_", "-"));
                        String country = fields[1];
                        Date depDate = Translator.formatDate(fields[2], dateFormat);
                        Date retDate = Translator.formatDate(fields[3], dateFormat);
                        String place = fields[4];
                        Double cost = Translator.formatPrice(contractorLoc, fields[5]);
                        String currencySymbol = fields[6];
                        this.OFFERS.add(new Offer(contractorLoc, country, depDate, retDate, place, cost, currencySymbol));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        this.OFFERS.forEach(offer -> {
                    String sb = Translator.translateCountry(offer.getLocale(), loc, offer.getCountry()) + " " +
                            Translator.formatDate(offer.getDepartureDate(), dateFormat) + " " +
                            Translator.formatDate(offer.getReturnDate(), dateFormat) + " " +
                            Translator.translatePlace(loc, offer.getPlace()) + " " +
                            Translator.formatPrice(loc, offer.getPrice()) + " " +
                            offer.getCurrency();
                    offers.add(sb);
                }
        );
        return offers;
    }

    public List<Offer> getOffersList() {
        return OFFERS;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public static class Translator {

        public static String translateCountry(Locale contractorLocale, Locale translatedLocale, String country) {
            return Arrays.stream(Locale.getAvailableLocales())
                    .filter(l -> l.getDisplayCountry(contractorLocale).equals(country))
                    .findFirst()
                    .map(l -> l.getDisplayCountry(translatedLocale))
                    .orElse(country);
        }

        public static Date formatDate(String date, String format) {
            SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
            df.applyPattern(format);
            try {
                return df.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        public static String formatDate(Date date, String format) {
            SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance();
            df.applyPattern(format);
            return df.format(date);
        }

        public static String translatePlace(Locale locale, String place) {
            ResourceBundle properties = ResourceBundle.getBundle("zad1/placesDictionary", locale);
            return properties.getString(place);
        }

        public static Double formatPrice(Locale contractorLocale, String price) {
            NumberFormat nfLocale = NumberFormat.getInstance(contractorLocale);
            try {
                return nfLocale.parse(price).doubleValue();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        public static String formatPrice(Locale translatedLocale, Double price) {
            NumberFormat nfLocale = NumberFormat.getInstance(translatedLocale);
            return nfLocale.format(price);
        }
    }
}
