package com.jsystems.qa.qaapi.service;

import com.jsystems.qa.qaapi.model.azure.author.AzureAuthor;
import com.jsystems.qa.qaapi.model.azure.book.Book;
import com.jsystems.qa.qaapi.specification.Specification;
import io.restassured.RestAssured;

public class BookService {

    public static void postBook(Book book, int statusCode){

        RestAssured.given()
                .spec(Specification.fakeAzureSpecBuilder())
                .when()
                .body(book)
                .post("/api/books")
                .then()
                .assertThat()
                .statusCode(statusCode)
                ;


    }


}
