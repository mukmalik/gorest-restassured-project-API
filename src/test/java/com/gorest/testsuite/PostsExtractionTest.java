package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest extends TestBase
{
    static ValidatableResponse response;
    @BeforeClass
    public void inIt()
    {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .when()
                .get("/posts")
                .then().statusCode(200);
    }
//             1. Extract the title
    @Test
    public void test001()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> allTitle = response.extract().path("title");
        System.out.println("All the title  "+allTitle);
        System.out.println("total number of titles   "+allTitle.size());
        System.out.println("------------------End of Test---------------------------");
    }


//             2. Extract the total number of record
    @Test
    public void test002()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> numberOdRecords = response.extract().path("data");
        System.out.println(numberOdRecords.size());
        System.out.println("------------------End of Test---------------------------");
    }

//             3. Extract the body of 15th record
    @Test
    public void test003()
    {
        System.out.println("------------------StartingTest---------------------------");
        String bodyOf = response.extract().path("[14].body");
        System.out.println(bodyOf);
        System.out.println("------------------End of Test---------------------------");
    }
//             4. Extract the user_id of all the records
    //System.out.println("------------------StartingTest---------------------------");
        //System.out.println("------------------End of Test---------------------------");
    @Test
    public void test004()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<Integer> allIds = response.extract().path("user_id");
        System.out.println("All user ids for all records allIds  "+allIds);
        System.out.println("Total number of user ids are   "+allIds.size());
        System.out.println("------------------End of Test---------------------------");
    }

//             5. Extract title of all the records
    @Test
    public void test005()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> allRecords = response.extract().path("title");
        System.out.println("Titale of all records "+allRecords);
        System.out.println("------------------End of Test---------------------------");
    }

//             6. Extract the title of all records whose user_id=5914200
    @Test
    public void test006()
    {

        List<String> allTitles = response.extract().path("findAll{it.user_id == 7015104}.title");
        System.out.println(allTitles);
    }
//             7. Extract the body of all records whose id 93957

    @Test
    public void test007()
    {
        List<String> allBody = response.extract().path("findAll{it.id == 140158}.body");
        System.out.println(allBody);
    }
}
