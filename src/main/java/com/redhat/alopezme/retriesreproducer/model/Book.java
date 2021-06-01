package com.redhat.alopezme.retriesreproducer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder({"id","title","author","publicationYear"})
public class Book implements Serializable {

    @JsonProperty("id")
    public int id;
    public String title;
    public String author;
    @JsonProperty("publicationYear")
    public int publicationYear;

    public Book(int id, String title, String author, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }

    @SneakyThrows
    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
