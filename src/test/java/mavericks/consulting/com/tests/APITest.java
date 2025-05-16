package mavericks.consulting.com.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import mavericks.consulting.com.model.Category;
import mavericks.consulting.com.model.Pet;
import mavericks.consulting.com.model.Tags;
import mavericks.consulting.com.base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class APITest extends BaseTest {
    private Pet responsePet;
    private long petId;
    private ObjectMapper mapper;
    private Pet getResponse;
    private static final Logger logger = LoggerFactory.getLogger(APITest.class);
    @Test
    public void addNewPetToStore() {
        mapper = new ObjectMapper();
        APIResponse response = context.post(properties.getProperty("base-uri-api"),
                RequestOptions.create()
                        .setHeader("accept", "application/json")
                        .setHeader("Content-Type", "application/json")
                        .setData(initializeRequestBody()));
        try {
            responsePet = mapper.readValue(response.body(), Pet.class);
        } catch (IOException e) {
            e.getStackTrace();
        }
        logger.info("Pet created with ID: {}",responsePet.getId());
        petId = responsePet.getId();
        Assert.assertEquals(response.status(), 200);
        Assert.assertEquals(responsePet.getCategory().getName(), initializeRequestBody().getCategory().getName());
        Assert.assertEquals(responsePet.getName(), initializeRequestBody().getName());
        Assert.assertEquals(responsePet.getPhotoUrls().get(0), initializeRequestBody().getPhotoUrls().get(0));
        Assert.assertEquals(responsePet.getTags().get(0).getId(), initializeRequestBody().getTags().get(0).getId());
        Assert.assertEquals(responsePet.getTags().get(0).getName(), initializeRequestBody().getTags().get(0).getName());
    }

    @Test
    public void findPetByID() {
        addNewPetToStore();
        APIResponse response = context.get(properties.getProperty("base-uri-api") + String.format("/%s", petId), RequestOptions.create().setHeader("accept", "application/json"));
        try {
            getResponse = mapper.readValue(response.body(), Pet.class);
        } catch (IOException e) {
            e.getStackTrace();
        }
        Assert.assertEquals(getResponse.getId(), petId);
        Assert.assertEquals(getResponse.getName(), responsePet.getName());
    }

    public Pet initializeRequestBody() {
        Pet pet = new Pet();
        pet.setId(0);
        pet.setName("doggie");
        pet.setStatus("available");
        Category category = new Category();
        category.setId(0);
        category.setName("string");
        pet.setCategory(category);
        Tags tags = new Tags();
        tags.setId(0);
        tags.setName("string");
        List<Tags> tagsList = new ArrayList<>();
        tagsList.add(tags);
        pet.setTags(tagsList);
        List<String> photoUrlsList = new ArrayList<>();
        photoUrlsList.add("string");
        pet.setPhotoUrls(photoUrlsList);
        return pet;
    }
}
