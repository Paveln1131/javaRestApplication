package com.example.javarestapiapplication.dto;

import java.time.LocalDate;
import java.time.Period;

public class PersonDto {
    private String firstName;
    private String lastName;
    private String birthNumber;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(String birthNumber) {
        this.birthNumber = birthNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int calculateAge() {
        String[] parts = birthNumber.split("[/]");
        String birthDateString = parts[0];


            int year = Integer.parseInt(birthDateString.substring(0, 2));
            int month = Integer.parseInt(birthDateString.substring(2, 4));
            int day = Integer.parseInt(birthDateString.substring(4, 6));

            // Získám aktuální rok a vezmu poslední dvě číslice
            int currentYearLastTwoDigits = LocalDate.now().getYear() % 100;

            // Rozhodnu o jaké století se jedná - pokud je rok z rodného číla větší, než auktuální rok (98 > (20)24), nastavím 20. století
            int century = (year > currentYearLastTwoDigits) ? 1900 : 2000;

            LocalDate birthDate = LocalDate.of(century + year, month, day);
            LocalDate currentDate = LocalDate.now();

            return Period.between(birthDate, currentDate).getYears();
        }
    }

