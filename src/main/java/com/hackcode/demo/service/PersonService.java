package com.hackcode.demo.service;

import com.google.cloud.bigquery.TableResult;
import com.hackcode.demo.client.BigQueryClient;
import com.hackcode.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class PersonService {

  @Autowired
  private BigQueryClient bigQueryClient;

  @Value("${google.cloud.project-id}")
  private String projectId;
  @Value("${google.cloud.dataset-id}")
  private String datasetId;

  private String tableName;

  @PostConstruct
  private void init(){
    this.tableName = String.format("`%s.%s.person`", projectId, datasetId);
  }

  public void savePerson(Person person) {
    person.setId(UUID.randomUUID().toString().replace("-", ""));
    String query = String.format(
            "INSERT INTO %s (id, name, email, age) VALUES ('%s', '%s', '%s', %d)",
            tableName, person.getId(), person.getName(), person.getEmail(), person.getAge()
    );
    bigQueryClient.query(query);
  }

  public List<Person> getAllPersons() {
    String query = String.format("SELECT id, name, email, age FROM %s", tableName);
    TableResult result = bigQueryClient.query(query);
    List<Person> persons = new ArrayList<>();
    result.iterateAll().forEach(row -> {
      String id = row.get("id").getStringValue();
      String name = row.get("name").getStringValue();
      String email = row.get("email").getStringValue();
      Integer age = row.get("age").getNumericValue().intValue();
      Person person = Person.builder().id(id).name(name).email(email).age(age).build();
      persons.add(person);
    });
    return persons;
  }

  public void updatePerson(Person person){
    String query = String.format(
            "UPDATE %s SET name = '%s', email = '%s', age = %d WHERE id = '%s'",
            tableName, person.getName(), person.getEmail(), person.getAge(), person.getId()
    );
    bigQueryClient.query(query);
  }

  public void deletePerson(String personId) {
    String query = String.format(
            "DELETE FROM %s WHERE id = '%s'",
            tableName, personId
    );
    bigQueryClient.query(query);
  }
}
