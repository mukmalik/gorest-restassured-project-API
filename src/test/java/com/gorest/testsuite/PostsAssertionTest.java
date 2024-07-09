package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase
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

       // 1. Verify if the total record is 25
    @Test
    public void test001()
    {
        response.body("data.size()", equalTo(25));

    }

      /*  2. Verify the if the title of id = 93997is equal to ”Demittoconqueror atavus argumentum corrupti
    cohaero libero.”*/
    @Test
    public void test002()
    {
        response.body("find{it.id == 139915}.title", equalTo("Defaeco in carbo decet audeo volutabrum corroboro."));
    }

       // 3. Check single user_id in the Array list (5914249)

    @Test
    public void test003()
    {
        response.body("id", hasItem(139914));
    }
        //4. Checkthe multiple ids in the ArrayList (5914243, 5914202, 5914199)

    @Test
    public void test004()
    {
        response.body("id", hasItems(139909,139908,139905));
    }
        /*5. Verify the body of userid = 5914197is equal “Desiderovoraxadsum.Nonconferoclarus.
    Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
    vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
    tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
    acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”*/

    @Test
    public void test005()
    {
        response.body("findAll{it.id == 139905}.body" , equalTo("Verto a vicinus. Uberrime ullus patria. Tardus temporibus testimonium. Ars est vix. Assentator trans suggero. Venio stillicidium deserunt. Circumvenio sponte cilicium. Blandior sortitus amicitia. Approbo audentia tollo. Attonbitus et spectaculum. Conicio sollicito solutio."));
    }

}
