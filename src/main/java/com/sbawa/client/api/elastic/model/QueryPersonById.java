package com.sbawa.client.api.elastic.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLArguments;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;


/**
 * @author simar bawa
 */

/*queryPersonById*/
@GraphQLProperty(name = "queryPersonById", arguments = {@GraphQLArgument(name = "id")} )
public class QueryPersonById {
    private String firstName;
    private Integer age;


    public QueryPersonById() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
