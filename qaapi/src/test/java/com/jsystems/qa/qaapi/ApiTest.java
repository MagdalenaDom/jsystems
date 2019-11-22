package com.jsystems.qa.qaapi;

import com.jsystems.qa.qaapi.model.azure.author.AzureAuthor;
import com.jsystems.qa.qaapi.model.azure.book.Book;
import com.jsystems.qa.qaapi.service.BookService;
import com.jsystems.qa.qaapi.service.UserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("ApiTest")
public class ApiTest {

    @Test
    public void firstApiTest(){

        RestAssured
                .given()
                .get("http://www.mocky.io/v2/5a6b69ec3100009d211b8aeb")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("Piotr"))
                .body("surname", equalTo("Kowalski"));
    }

    @Test
    @DisplayName("Should returns correctly user list")
    public void shouldReturnsUserList(){
        RestAssured
                .given()
                .get("http://www.mocky.io/v2/5a6a58222e0000d0377a7789")
                .then()
                .assertThat()
                .statusCode(200)
                .body("[0].imie", notNullValue())
                .body("[0].imie", equalTo("Piotr"))
                .body("[0].nazwisko", notNullValue())
                .body("[0].nazwisko", equalTo("Kowalski"));

    }

    @Test
    @DisplayName("Should returns correctly user list using jsonPath mapping")
    public void jsonPathTest(){
        List<User> users = UserService.getUsers();
//                .given()
//                .get("http://www.mocky.io/v2/5a6a58222e0000d0377a7789")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .extract()
//                .body()
//                .jsonPath()
//                .getList("", User.class);

        assertTrue(users.get(0).imie.equals("Piotr"));
        assertTrue(users.get(0).nazwisko.equals("Kowalski"));
        assertTrue(users.get(0).device.get(0).type.equals("computer"));
        assertTrue(users.get(0).device.get(0).deviceModel.get(0).screenSize == 17);
        assertTrue(users.size() > 0);

    }

    @Test
    @DisplayName("Get azure authors")
    public void shouldReturnsAllAzureAuthorsList(){
        List<AzureAuthor> azureAuthors = UserService.getAzureAuthors();

        assertThat(azureAuthors.size()).isGreaterThan(0);

        for(AzureAuthor azureAuthor : azureAuthors){
            int firstNameId = Integer.parseInt(azureAuthor.firstName.replace("First Name", ""));
            assertThat(azureAuthor.firstName).contains("First Name");
            assertThat(azureAuthor.firstName).matches("First Name");
        }
    }

    @Test
    @DisplayName("Post Book test")
    public void postBookTest(){
        Book book = new Book(1, "Jsystems", "Szkolenia", 382, "en", "2019-11-22T09:41:54.440Z");

        BookService.postBook(book, 200);
    }

}

