package com.codedecode.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sequence {

    @Id
    private String id;


    private int sequence;
}
