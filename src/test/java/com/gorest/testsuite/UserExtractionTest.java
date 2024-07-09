package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase
{
    static ValidatableResponse response;

    @BeforeClass
    public void inIt()
    {
       RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/users")
                .then().statusCode(200);
    }


//     1. Extract the All Ids
    @Test
    public void test001()
    {
        List<Integer> allIds = response.extract().path("id");
    }
//     2. Extract the all Names
    @Test
    public void test002()
    {
        List<String> allNames = response.extract().path("name");
    }

//     3. Extract the name of 5th object
    @Test
    public void test003()
    {
      String firstame =  response.extract().path("[1].name");
        System.out.println(firstame);
    }

//     4. Extract the names of all object whose status = inactive
    @Test
    public void test004()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> name = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("the names of all object whose status = inactive "+name);
        System.out.println("total number of object whose status = inactive "+name.size());
        System.out.println("------------------End of Test---------------------------");
    }
//     5. Extract the gender of all the object whose status = active
    @Test
    public void test005()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("the names of all object whose status = active "+gender);
        System.out.println("total number of object whose status = active "+gender.size());
        System.out.println("------------------End of Test---------------------------");
    }

//     6. Print the names of the object whose gender = female
    @Test
    public void test006()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> names = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("the names of all object whose gender = female "+names);
        System.out.println("total number of object whose gender = female "+names.size());
        System.out.println("------------------End of Test---------------------------");

    }
//     7. Get all the emails of the object where status = inactive
    @Test
    public void test007()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> allEmails = response.extract().path("findAll{it.status == 'inactive'}.emails");
        System.out.println("get all email of object where status = inactive "+allEmails);
        System.out.println("total number of all email where status = inactive "+allEmails.size());
        System.out.println("------------------End of Test---------------------------");

    }

//     8. Get the ids of the object where gender = male
    @Test
    public void test008()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> allIds = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("get all ids of object where gender = male "+allIds);
        System.out.println("total number ids where gender is male "+allIds.size());
        System.out.println("------------------End of Test---------------------------");

    }
//     9. Get all the status
    @Test
        public void test009() {
        System.out.println("------------------StartingTest---------------------------");
        List<String> allStatus = response.extract().path("status");
        System.out.println("List of all status "+allStatus);
        System.out.println("number of objects "+allStatus.size());
        System.out.println("------------------End of Test---------------------------");
    }
//    10. Get email of the object where name = Gorakhanatha Pillai
    @Test
    public void test010()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> emGora = response.extract().path("findAll{it.name == 'Gorakhanatha Pillai'}.email");
        System.out.println("email of Gorakhanatha Pillai is  "+emGora);
        System.out.println("------------------End of Test---------------------------");
    }
//    11. Get gender of id = 5914189
    @Test
    public void test011()
    {
        System.out.println("------------------StartingTest---------------------------");
        List<String> genId = response.extract().path("findAll{it.id == 7015044}.gender");
        System.out.println("Gender of 7015044 is  "+ genId);
        System.out.println("------------------End of Test---------------------------");

    }



}
