package com.example.javarestapiapplication.service;
import com.example.javarestapiapplication.domain.Person;
import com.example.javarestapiapplication.dto.PersonDto;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersonService {

    private final Map<String, Person> personMap = new HashMap<>();

    private String normalizeBirthNumber(String birthNumber) {
        return birthNumber.replace("/", "");
    }
    public Person addPerson(Person person) {
        if (person.getFirstName().isEmpty() || person.getLastName().isEmpty()) {
            throw new IllegalArgumentException("First name and last name cannot be empty.");
        }

        //Doufám, že regex není zbytečně složitý, pro jistotu píšu doc
        /**
         * \\d - libovolné číslo od 0 do 9
         * {6} | {4} - počet čísel
         * První část logického výrazu kontroluje formát s lomítkem, a druhý bez
         */
        if (!person.getBirthNumber().matches("\\d{6}/\\d{4}") && !person.getBirthNumber().matches("\\d{6}\\d{4}")) {
            throw new IllegalArgumentException("Invalid birth number format.");
        }

        String normalizedBirthNumber = normalizeBirthNumber(person.getBirthNumber());

        if (personMap.containsKey(normalizedBirthNumber)) {
            throw new IllegalArgumentException("Person with this birth number already exists.");
        }

        personMap.put(normalizedBirthNumber, person);
        return person;
    }

    public PersonDto getPerson(String birthNumber) {
        String normalizedBirthNumber = normalizeBirthNumber(birthNumber);

        if (!personMap.containsKey(normalizedBirthNumber)) {
            throw new IllegalArgumentException("Person not found.");
        }

        Person person = personMap.get(normalizedBirthNumber);

        PersonDto personDto = new PersonDto();
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        personDto.setBirthNumber(person.getBirthNumber());
        personDto.setAge(personDto.calculateAge());

        return personDto;
    }

    public Person deletePerson(String birthNumber) {
        String normalizedBirthNumber = normalizeBirthNumber(birthNumber);

        if (!personMap.containsKey(normalizedBirthNumber)) {
            throw new IllegalArgumentException("Person not found.");
        }

        return personMap.remove(normalizedBirthNumber);
    }
}
