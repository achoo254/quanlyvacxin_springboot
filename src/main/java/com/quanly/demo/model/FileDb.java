package com.quanly.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Files")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FileDb {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String fileDbId;
    private String name;

    private String type;

    @Lob
    private byte[] data;

    public FileDb() {

    }

    public FileDb(String fileName, String contentType, byte[] bytes) {
        this.name = fileName;
        this.type = contentType;
        this.data = bytes;
    }


}
