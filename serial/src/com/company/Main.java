package com.company;

import java.io.*;
class Country  implements Serializable {
    public String country;

    public Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public static class City implements Serializable{
        private String city_name;
        private int population;
        private Country country;
        private String capital_of_country;

        public City(String city_name,int population, String capital_of_country, Country country){
            this.city_name=city_name;
            this.population=population;
            this.country=country;
            this.capital_of_country=capital_of_country;
        }
        @Override
        public String toString(){
            return "Country{"+
                    "name of city: " + city_name +
                    ", population: " + population+
                    ", capital of country: " + capital_of_country+
                    ", Country: " + country+
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Country country=new Country("Russia");
        Country country1= new Country("Canada");
        City msc= new City("Москва",12700000,"Москва", country);
        City spb= new City("Санкт-Петербург", 4900000, "Москва", country);
        City van= new City("Ванкувер", 631000,"Оттава", country1);
        ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream("Country.out"));
        oos.writeObject(msc);
        oos.writeObject(spb);
        oos.writeObject(van);
        oos.close();

        ObjectInputStream ois= new ObjectInputStream(new FileInputStream("Country.out"));
        City mscRestored=(City) ois.readObject();
        City spbRestored=(City)ois.readObject();
        City vanRestored=(City)ois.readObject();
        ois.close();

        ByteArrayOutputStream baos= new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(baos);
        oos2.writeObject(msc);
        oos2.writeObject(spb);
        oos2.writeObject(van);
        oos2.flush();

        ObjectInputStream ois2= new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
        City mscRestoredFB=(City)ois2.readObject();
        City spbRestoredFB=(City)ois2.readObject();
        City vanRestoredFB=(City)ois2.readObject();
        ois2.close();

        System.out.println("Before Serialize: " + "\n" + msc + "\n" + spb+"\n"+van);
        System.out.println("After Restored From Byte: " + "\n" + mscRestoredFB + "\n" + spbRestoredFB+ "\n"+ vanRestoredFB);
        System.out.println("After Restored: " + "\n" + mscRestored + "\n" + spbRestored+"\n"+vanRestored);
    }
}
