import de.bookstore.TestDataService

class BootStrap {

    TestDataService testDataService

    def init = { servletContext ->
        testDataService.createTestData()
    }

    def destroy = {
    }

}
