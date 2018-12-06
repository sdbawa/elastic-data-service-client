package com.sbawa.client.api.elastic.model;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * @author simar bawa
 */

/*mutatePersonNameAge*/
@GraphQLProperty(name = "mutatePersonNameAge", arguments = {@GraphQLArgument(name = "firstName"), @GraphQLArgument(name = "age")} )
public class MutationPersonNameAge {

    private String firstName;
    private Integer age;

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
