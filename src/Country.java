/**
 * Created by PiratePowWow on 2/12/16.
 */
public class Country {
    private String abbreviation;
    private String country;

    @Override
    public String toString(){
        return this.abbreviation + "|" + this.country + "\n";
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
