package com.examly.toystore;


import java.lang.annotation.Annotation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.io.File;


@SpringBootTest(classes = ToystoreApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToystoreApplicationTests {
    
    
    @Autowired
    private MockMvc mockMvc;
    
    private static final String LOG_FOLDER_PATH = "logs";
    private static final String LOG_FILE_PATH = "logs/application.log";

    @Test
    @Order(1)
    void CRUD_testModelFolder() {
        String directoryPath = "src/main/java/com/examly/toystore/model";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }
    
    @Test
    @Order(2)
    void CRUD_testCategoryModelFile() {
        String filePath = "src/main/java/com/examly/toystore/model/Category.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(3)
    void CRUD_testToyModelFile() {
        String filePath = "src/main/java/com/examly/toystore/model/Toy.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }


    @Test
    @Order(4)
    void CRUD_testServiceFolder() {
        String directoryPath = "src/main/java/com/examly/toystore/service";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    @Order(5)
    void CRUD_testCategoryServiceFile() {
        String filePath = "src/main/java/com/examly/toystore/service/CategoryService.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(6)
    void CRUD_testToyServiceFile() {
        String filePath = "src/main/java/com/examly/toystore/service/ToyService.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(7)
    void CRUD_testControllerFolder() {
        String directoryPath = "src/main/java/com/examly/toystore/controller";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }
    
    @Test
    @Order(8)
    void CRUD_testCategoryControllerFile() {
        String filePath = "src/main/java/com/examly/toystore/controller/CategoryController.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(9)
    void CRUD_testToyControllerFile() {
        String filePath = "src/main/java/com/examly/toystore/controller/ToyController.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }


    @Test
    @Order(10) 
    void CRUD_testRepositoryFolder() {
        String directoryPath = "src/main/java/com/examly/toystore/repository";
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }

    @Test
    @Order(11)
    void CRUD_testCategoryRepositoryFile() {
        String filePath = "src/main/java/com/examly/toystore/repository/ToyRepository.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(12)
    void CRUD_testToyRepositoryFile() {
        String filePath = "src/main/java/com/examly/toystore/repository/ToyRepository.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }

    @Test
    @Order(13)
    void CRUD_testCheckEmptyCategory() throws Exception {
        mockMvc.perform(get("/categories/checkEmpty")
        .content("Category is Empty")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent()); // 204 status
    }

    @Test
    @Order(14)
    void CRUD_testCheckEmptyToy() throws Exception {
        mockMvc.perform(get("/toys/checkEmpty")
        .content("Toy is Empty")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent()); // 204 status
    }
    
    @Test
    @Order(15)
    void CRUD_testCreateActionsCategory() throws Exception {
        String categoryJson = "{\"name\": \"Action Figures\"}";
        
        mockMvc.perform(post("/categories")
        .contentType(MediaType.APPLICATION_JSON)
        .content(categoryJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Action Figures"))
        .andExpect(jsonPath("$.id").exists());
    }
    
    @Test
    @Order(16)
    void CRUD_testCreateAnimalsCategory() throws Exception {

        String categoryJson = "{\"name\": \"Animals\"}";
        String categoryJsonDummy = "{\"name\": \"Fruits\"}";

        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Animals"))
                .andExpect(jsonPath("$.id").exists());
        
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJsonDummy))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(17)
    void CRUD_testCreateAfricanDollToy() throws Exception {
        String toyJson = "{ \"name\": \"African Doll\", \"price\": 15.99}";

        mockMvc.perform(post("/toys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toyJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("African Doll"))
        .andExpect(jsonPath("$.price").value(15.99));
    }
    
    @Test
    @Order(18)
    void CRUD_testCreateKazooToy() throws Exception {

        String toyJson = "{\"name\": \"Kazoo\", \"price\": 2.55}";
        String toyJsonDummy = "{\"name\": \"Dummy\", \"price\": 50}";
        
        mockMvc.perform(post("/toys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toyJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Kazoo"))
        .andExpect(jsonPath("$.price").value(2.55));
        
        mockMvc.perform(post("/toys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toyJsonDummy))
        .andExpect(status().isCreated());
    }
    
    @Test
    @Order(19)
    void CRUD_testGetAllCategories() throws Exception {

        mockMvc.perform(get("/categories")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Action Figures"))
                .andExpect(jsonPath("$[1].name").value("Animals"));
    }

    @Test
    @Order(20)
    void CRUD_testGetAllToys() throws Exception {
 
        mockMvc.perform(get("/toys")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].name").value("African Doll"))
            .andExpect(jsonPath("$[1].name").value("Kazoo"));

    }


    @Test
    @Order(21)
    void CRUD_testUpdateCategoryById() throws Exception {
        String updatedCategoryJson = "{\"name\": \"Action Heros\"}";
        
        mockMvc.perform(put("/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCategoryJson))
                .andExpect(status().isOk())  // Expect 200 OK status
                .andExpect(jsonPath("$.name").value("Action Heros"));  // Check that the name is updated
    }


    @Test
    @Order(22)
    void CRUD_testUpdateToyById() throws Exception {
        String updatedToyJson = "{\"name\": \"Indian Hulk\", \"price\": 14.99}";
        
        mockMvc.perform(put("/toys/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(updatedToyJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Indian Hulk"))
        .andExpect(jsonPath("$.price").value(14.99));
    }
    

    @Test
    @Order(23)
    void CRUD_testGetToyById() throws Exception {

        mockMvc.perform(get("/toys/1", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Expect 200 OK status
                .andExpect(jsonPath("$.id").value(1))  // Verify the ID
                .andExpect(jsonPath("$.name").value("Indian Hulk"))  // Verify the toy name
                .andExpect(jsonPath("$.price").value(14.99));  // Verify the toy price
    }

    @Test
    @Order(24)
    void testGetCategoryById() throws Exception {

        mockMvc.perform(get("/categories/2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Expect 200 OK status
                .andExpect(jsonPath("$.id").value(2))  // Verify the ID
                .andExpect(jsonPath("$.name").value("Animals"));
    }

    @Test
    @Order(25)
    void CRUD_testGetToyByIdNotFound() throws Exception {
        mockMvc.perform(get("/toys/12", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // Expect 404 Not Found status
    }

    @Test
    @Order(26)
    void testGetToyByIdNotFound() throws Exception {
        mockMvc.perform(get("/toys/14")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());  // Expect 404 Not Found status
    }


    @Test
    @Order(27)
    void CRUD_testDeleteCategoryById() throws Exception {
        mockMvc.perform(delete("/categories/3"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Deleted Successfully"));  
    }

    @Test
    @Order(28)
    void CRUD_testDeleteToyById() throws Exception {
        mockMvc.perform(delete("/toys/3"))
                .andExpect(status().isNoContent())
                .andExpect(content().string("Deleted Successfully"));  
    }

    @Test
    @Order(29)
    void CRUD_testDeleteCategoryByIdNegative() throws Exception {
        mockMvc.perform(delete("/categories/10"))
                .andExpect(status().isNotFound()); 
    }

    @Test
    @Order(30)
    void CRUD_testDeleteToyByIdNegative() throws Exception {
        mockMvc.perform(delete("/toys/10"))
                .andExpect(status().isNotFound()); 
    }

    @Test
    @Order(31)
    void Mapping_testConfigHasAnnotation() {
        checkAnnotationExists("com.examly.toystore.model.Toy", "org.springframework.context.annotation.ManyToOne");
    }

    @Test
    @Order(32)
    void Mapping_testConfigHasAnnotationOneToMany() {
        checkAnnotationExists("com.examly.toystore.model.Toy", "org.springframework.context.annotation.OneToMany");
    }


    @Test
    @Order(33)
    void Mapping_testCreateToySuperWithMapping() throws Exception {
        String toyJson = "{\"name\": \"Super Man\", \"price\": 15.99, \"category\": {\"id\": 1}}";
        
        mockMvc.perform(post("/toys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toyJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Super Man"))
        .andExpect(jsonPath("$.price").value(15.99))
        .andExpect(jsonPath("$.category.id").value(1));
    }
    

    @Test
    @Order(34)
    void Mapping_testCreateCatToy() throws Exception {
        String toyJson = "{\"name\": \"Cat\", \"price\": 12.49, \"category\": {\"id\": 2}}";
        String toyJsonDummy = "{\"name\": \"Dog\", \"price\": 100, \"category\": {\"id\": 2}}";
        String toyJsonBat = "{\"name\": \"Bat Man\", \"price\": 400, \"category\": {\"id\": 1}}";
        
        mockMvc.perform(post("/toys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toyJson))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.name").value("Cat"))
        .andExpect(jsonPath("$.price").value(12.49))
        .andExpect(jsonPath("$.category.id").value(2));
        
        mockMvc.perform(post("/toys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toyJsonDummy))
        .andExpect(status().isCreated());
        
        mockMvc.perform(post("/toys")
        .contentType(MediaType.APPLICATION_JSON)
        .content(toyJsonBat))
        .andExpect(status().isCreated());
    }
    
    
    @Test
    @Order(35)
    void Mapping_testGetAllCategories() throws Exception {
            mockMvc.perform(get("/categories"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Action Heros"))
        .andExpect(jsonPath("$[1].name").value("Animals"));
    }
    

    @Test
    @Order(36)
    void Mapping_testGetAllToys() throws Exception {
        mockMvc.perform(get("/toys"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Indian Hulk"))
        .andExpect(jsonPath("$[1].name").value("Kazoo"))
        .andExpect(jsonPath("$[2].name").value("Super Man"))
        .andExpect(jsonPath("$[3].name").value("Cat"));
    }
    
    @Test
    @Order(37)
    void Mapping_testGetToyById() throws Exception {
        mockMvc.perform(get("/toys/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Kazoo"))
        .andExpect(jsonPath("$.price").value(2.55));
    }
    
    @Test
    @Order(38)
    void Mapping_testGetCategoryOfToy() throws Exception {
        mockMvc.perform(get("/toys/4/category"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Action Heros"));
    }

    @Test
    @Order(39)
    void Mapping_testUpdateToyById() throws Exception {
        String updatedToyJson = "{\"category\": {\"id\": 1}}";
        
        mockMvc.perform(put("/toys/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(updatedToyJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Indian Hulk"))
        .andExpect(jsonPath("$.price").value(14.99))
        .andExpect(jsonPath("$.category.id").value(1));
    }

    @Test
    @Order(40)
    void Mapping_testDeleteToyById() throws Exception {
        mockMvc.perform(delete("/toys/2"))
        .andExpect(content().string("Deleted Successfully"))
        .andExpect(status().isNoContent());
    }
    
    @Test
    @Order(41)
    void Mapping_testDeleteToyByIdNegative() throws Exception {
        mockMvc.perform(delete("/toys/23"))
                .andExpect(content().string("No records available for deletion"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @Order(42)
    void testDeleteCategoryById() throws Exception {
        mockMvc.perform(delete("/categories/2"))
        .andExpect(content().string("Deleted Successfully"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(43)
    void Mapping_testCountTotalCategories() throws Exception {
        mockMvc.perform(get("/categories/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    @Order(44)
    void PageAndJPQL_testCountToysByCategoryId() throws Exception {
        mockMvc.perform(get("/toys/count-by-category/1"))
        .andExpect(content().string("3"))
        .andExpect(status().isOk());
    }
    

    @Test
    @Order(45)
    void PageAndJPQL_testTransientAnnotation() {
        checkAnnotationExists("com.examly.toystore.controller.CategoryController", "org.springframework.context.annotation.Transient");
    }

    @Test
    @Order(46)
    void PageAndJPQL_testTransientToyAnnotation() {
        checkAnnotationExists("com.examly.toystore.controller.ToyController", "org.springframework.context.annotation.Transient");
    }


    @Test
    @Order(47)
    void PageAndJPQL_testGetCategoriesByToyCount() throws Exception {
        mockMvc.perform(get("/categories/toy-count/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Action Heros"));
    }

    @Test
    @Order(48)
    void PageAndJPQL_testGetCategoriesByToyCountNegative() throws Exception {
        mockMvc.perform(get("/categories/toy-count/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    @Order(49)
    void Log_testLogFolderAndFileCreation() {
        // Check if the "logs" folder exists
        File logFolder = new File(LOG_FOLDER_PATH);
        assertTrue(logFolder.exists(), "Log folder should be created");
 
        // Check if the "application.log" file exists inside the "logs" folder
        File logFile = new File(LOG_FILE_PATH);
        assertTrue(logFile.exists(), "Log file should be created inside 'logs' folder");
    }



    @Test
    @Order(50)
    void Log_testConfigurationFolder() {
        String directoryPath = "src/main/java/com/examly/toystore/configuration"; // Replace with the path to your directory
        File directory = new File(directoryPath);
        assertTrue(directory.exists() && directory.isDirectory());
    }
   
    @Test
    @Order(51)
    void Log_testConfigFile() {
        String filePath = "src/main/java/com/examly/toystore/configuration/SwaggerConfig.java";
        File file = new File(filePath);
        assertTrue(file.exists() && file.isFile());
    }
    
    
    
    @Test
    @Order(52)
    void Log_testSwaggerUIEndpointIsAccessible() throws Exception {
        // Make a GET request to the Swagger UI URL using MockMvc
        mockMvc.perform(get("/swagger-ui/index.html"))
        .andExpect(status().isOk()); // Assert that the status is 200 OK
    }

    
    @Test
    @Order(53)
    void testDeleteAllCategories() throws Exception {
        mockMvc.perform(delete("/categories"))
                .andExpect(status().isNoContent());
    }
    
    


    public void checkAnnotationExists(String className, String annotationClassName) {
        try {
            // Load the class and the annotation class
            Class<?> clazz = Class.forName(className);
            Class<?> annotationClass = Class.forName(annotationClassName);
            
            // Check if the annotation is present on the class
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) annotationClass)) {
                System.out.println("Annotation " + annotationClassName + " is present on " + className);
            } else {
                System.out.println("Annotation " + annotationClassName + " is NOT present on " + className);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

     
}
