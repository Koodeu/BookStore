package koodeu.BookStore;

import lombok.Getter;

@Getter
public enum Countries {

    POLAND("Polska", "PL"),
    UKRAINE("Ukraina", "UA"),
    AUSTRALIA("Australia", "AU"),
    GERMANY("Niemcy", "DE");

    private String polishName;
    private String symbol;

    Countries(String polishName, String symbol) {
        this.polishName = polishName;
        this.symbol = symbol;
    }
}
