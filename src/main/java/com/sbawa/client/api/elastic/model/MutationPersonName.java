package com.sbawa.client.api.elastic.model;

import io.aexp.nodes.graphql.annotations.GraphQLArgument;
import io.aexp.nodes.graphql.annotations.GraphQLProperty;

/**
 * @author simar bawa
 */

/*mutatePersonNameAge*/
@GraphQLProperty(name = "mutationPersonName", arguments = {@GraphQLArgument(name = "firstName")} )
public class MutationPersonName {

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
