package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class UserAssertionTest extends TestBase
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


//            1. Verify if the total record is 20
    @Test
    public void test001()
    {
        List<String> data = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        response.body("data.size()", equalTo(20));
        System.out.println("The size of the data is : "+data.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //            2. Verify the if the name of id = 5914197is equal to ”BhilanganaDhawan”
    // "id": 7015045,
    //        "name": "Anang Chopra"

    @Test
    public void test002()
    {
        System.out.println("------------------StartingTest---------------------------");
        response.body("find{it.name == 'Anang Chopra'}.id", equalTo(7015045));
       //response.body("[0].id",equalTo(7015045));
        System.out.println("------------------End of Test---------------------------");



    }


//            3. Check the single ‘Name’ in the Array list (DevBhattacharya)
    @Test
    public void test003()
    {
        System.out.println("------------------StartingTest---------------------------");

        response.body("[1].name", equalTo("Deeptimoyee Nair"));

        System.out.println("------------------End of Test---------------------------");
    }

//            4. Check multiple ‘Names’ in the ArrayList (UshaKaulEsq., Akshita Mishra, Chetanaanand Reddy )
    @Test
    public void test004()
    {
        System.out.println("------------------StartingTest---------------------------");
        response.body("name", hasItems("Deeptimoyee Nair","Menka Marar","Nawal Johar","Daevika Menon"));
        System.out.println("------------------End of Test---------------------------");

    }


//            5. Verify the emai of userid = 5914185is equal “tandon_iv_aanandinii@prosacco.example”
    @Test
    public void test005()
    {
        System.out.println("------------------StartingTest---------------------------");
        response.body("find{it.id == 7015047}.email",equalTo("daevika_menon@feil.example"));
        System.out.println("------------------End of Test---------------------------");

    }

//            6. Verify the status is “Active” of user name is “AmareshRana”
    @Test
    public void test006()
    {
        System.out.println("------------------StartingTest---------------------------");
        response.body("find{it.name == 'Daevika Menon'}.status",equalTo("inactive"));
        System.out.println("------------------End of Test---------------------------");
    }
//            7. Verify the Gender = male of user name is “DhanalakshmiPothuvaal”
    @Test
    public void test007()
    {
        System.out.println("------------------StartingTest---------------------------");
        response.body("find{it.name == 'Daevika Menon'}.gender",equalTo("female"));
        System.out.println("------------------End of Test---------------------------");
    }

}
