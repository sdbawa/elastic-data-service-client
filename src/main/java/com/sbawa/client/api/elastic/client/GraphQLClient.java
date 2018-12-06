package com.sbawa.client.api.elastic.client;

import com.google.gson.Gson;
import com.sbawa.client.api.elastic.model.MutationPersonNameAge;
import com.sbawa.client.api.elastic.model.QueryPersonById;
import io.aexp.nodes.graphql.Argument;
import io.aexp.nodes.graphql.Arguments;
import io.aexp.nodes.graphql.GraphQLRequestEntity;
import io.aexp.nodes.graphql.GraphQLTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author simar bawa
 */

@Component
public class GraphQLClient {


    RestTemplate restTemplate;

    private String graphQl_Url = "http://localhost:8080/graphql";

    GraphQLTemplate graphQLTemplate = new GraphQLTemplate();

    private final Logger logger = LoggerFactory.getLogger(GraphQLClient.class);

    public GraphQLClient() {
        this.graphQLTemplate  =  new GraphQLTemplate();
        this.restTemplate     =  new RestTemplate();
    }

    /**
     * This method is responsible for handling GraphQL Api  :  queryPersonById.
     *
     * @return
     */
    public QueryPersonById queryPersonById() {
        QueryPersonById  person  =  null;

        try {

            GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                            .url(graphQl_Url)
                            .request(QueryPersonById.class)
                            .arguments(new Arguments("queryPersonById", new Argument("id", "101")))
                            .build();

            logger.info("requestEntity {} " + requestEntity);

            String requestStr = convertRequestEntityToString(requestEntity);

            String response = restTemplate.postForObject(graphQl_Url, requestStr, String.class);
            logger.info("Received Response from Server {}", response);

            Map<String, Object> responseMap  = convertResponseToMap(response, "queryPersonById");
            person = new QueryPersonById();
            person.setFirstName((String)responseMap.get("firstName"));
            /*Issue with GSON parser as it reads all integers as doubles. Please use Jackson Mapper instead if possible*/
            Double ageDouble = (Double)responseMap.get("age");
            person.setAge(ageDouble.intValue());

            logger.info("response map {}", responseMap);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return person;
    }

    /**
     * This method is responsible for handling GraphQL Api  :  mutatePersonNameAge.
     * @return
     */
    public QueryPersonById mutatePersonNameAge() {
        QueryPersonById person = null;

        try {

            GraphQLRequestEntity requestEntity = GraphQLRequestEntity.Builder()
                            .url(graphQl_Url)
                            .arguments(
                                            new Arguments("mutatePersonNameAge", new Argument("firstName", "temp1"), new Argument("age", 22)    )   )
                            .request(MutationPersonNameAge.class)
                            .requestMethod(GraphQLTemplate.GraphQLMethod.MUTATE)
                            .build();


            String requestStr = convertRequestEntityToString(requestEntity);

            String response = restTemplate.postForObject(graphQl_Url, requestStr, String.class);
            logger.info("Received Response from Server {}", response);

            Map<String, Object> responseMap  = convertResponseToMap(response, "mutatePersonNameAge");
            person = new QueryPersonById();
            person.setFirstName((String)responseMap.get("firstName"));
            /*Issue with GSON parser as it reads all integers as doubles. Please use Jackson Mapper instead if possible*/
            Double ageDouble = (Double)responseMap.get("age");
            person.setAge(ageDouble.intValue());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return person;

    }

    //helper methods

    private String convertRequestEntityToString(GraphQLRequestEntity requestEntity) {
        String requestStr;
        Gson gson1 = new Gson();
        String request = gson1.toJson(requestEntity.getRequest());
        requestStr = request.substring( 1,request.length() - 1 ); //remove trailing and ending "
        logger.info("json of request {} " + request);
        return requestStr;
    }

    private Map<String, Object> convertResponseToMap(String response, String operationName) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map = (Map<String, Object>) gson.fromJson(response, map.getClass());

        Map<String, Object> map2 = new HashMap<>();
        map2 = (Map<String, Object>)map.get(operationName);

        return map2;
    }


}
