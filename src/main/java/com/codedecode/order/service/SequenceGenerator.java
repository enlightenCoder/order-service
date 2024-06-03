package com.codedecode.order.service;

import com.codedecode.order.entity.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations mongoOperations;

    public int generateNextOrderId(){

        Sequence counter = mongoOperations.findAndModify(
                // Query to find the document with _id equal to "sequence"
                Query.query(where("_id").is("sequence")),
                // Update to increment the "sequence" field by 1
                new Update().inc("sequence", 1),
                // Options to return the new document and to perform an upsert if no document is found
                options().returnNew(true).upsert(true),
                // The class type of the document to return
                Sequence.class
        );

        assert counter != null;

        return counter.getSequence();
    }
}
