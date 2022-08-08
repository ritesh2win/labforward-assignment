<h1>Overview</h1>

# Overview

The labforward assignment is service which is provides a capability to get word frequency/count along with the
suggestions given you have provided a list of words.

## Feature Overview

### User Story
- As a researcher, I would like to view the frequency of a given word and any similar
  words in my lab notebook entry.

### List of Feature(s)
- Determine frequency of word occurrence in notebook Entry
- Determine similar words in notebook Entry

###  Acceptance Criteria(s)
- Given a basic notebook entry with the text “Word Words Wor word"
  When the frequency of the word “Word” is requested
  Then the frequency is determined to be `1`
  And the list of similar words is determined to be “Words", "Wor", "word"
- Given a basic notebook entry with the text “Word Word Word word"
  When the frequency of the word “Word” is requested
  Then the frequency is determined to be `3`
  And the list of similar words is determined to be “word"


## Time Dedicated

|                Task Name                 | Feature | Time Spent (in minutes) | Test Cases |
|:----------------------------------------:|:-------:|:-----------------------:|:----------:|
|          Setup initial project           |   1,2   |         40 min          |     NA     |
|  Add unit test and code for controller   |   1,2   |         40 min          |    done    |
|       Add Utility and Service code       |   1,2   |         50 min          |    done    |
| Add Custom Exception Handling with tests |   1,2   |         30 min          |    done    |
|              Documentation               |   1,2   |         30 min          |     NA     |


## Library Integration

|       Library/Plugins Name       | Version |                      Purpose                       |
|:--------------------------------:|:-------:|:--------------------------------------------------:|
|    spring-boot-starter-parent    |  2.7.2  | to get all feature which needed to create rest api |
|  spring-boot-starter-validation  |  2.7.2  |           spring core validation library           |
| org.apache.commons: commons-text |   1.9   |    utility library to find levenshtein distance    |
|     spring-boot-starter-test     |  2.7.2  |           default spring test framework            |
|    com.coveo:fmt-maven-plugin    |  2.10   |                   code formatter                   |


## Testing

### Unit Tests

For unit test we are using junit 5. We will use the spring-boot-starter-test which supports the junit5 components.
Junit5 is a powerful testing tool with multiple feature like assertJ.

When writing unit test , we have used MockitoExtension and mock everything that we can. while using MockitoExtension, we
should avoid SpringExtension or SpringBootTest because they will be creating spring context which will make our test longer
to run.

```java
@ExtendWith({MockitoExtension.class})
@SpringBootTest
public class MyServiceTest {

    @Mock
   private OtherService otherService;

    @InjectMocks
    private MyService myService;

    @Test
    void testMyServiceMethod_WithSomeInput_ExpectedOutput()
    {
        //some test code here
    }
}
```

### Integration Tests

Integration test are more complex tests because we are using the capabilities of spring boot test library to spring up a spring context with web
context in memory. These test are intended to validate that all components of the service works well together.

```java

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"integration"})
@AutoConfigureMockMvc
public class MyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void myTest()
    {
        //some test code 
    }
}

```

## Future Enhancement

List of all functionalities that can be added in future


| Functionalities/Capabilities | Domain  |   Status    |
|:----------------------------:|:-------:|:-----------:|
|        Flow  Diagram         | Product | To be Added |
|       Acceptance Test        |  Eng.   | To be Added |
|       Performance Test       |  Eng.   | To be Added |
|    Swagger Documentation     |  Eng.   | To be Added |
|         Flow Diagram         |  Eng.   | To be Added |
