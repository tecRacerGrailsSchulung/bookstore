package de.bookstore

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.context.i18n.LocaleContextHolder
import spock.lang.Specification

import java.text.SimpleDateFormat

@TestFor(LendService)
@Mock([Book, Author, Lend, Customer])
class LendServiceSpec extends Specification {

    TestDataService testDataService
///dies ist ein test
    def setup() {
        testDataService = new TestDataService()
        testDataService.createTestData()
    }

    def cleanup() {
    }

    def "it should lend a book successfully"() {

        setup:
        def now = new Date()
        service.dateService = Mock(DateService)
        service.dateService.now() >> now

        and:
        def customer = new Customer()
        def formatter = SimpleDateFormat.getDateInstance(SimpleDateFormat.DEFAULT, LocaleContextHolder.locale)
        def days = grailsApplication.config.getProperty('de.bookstore.defaultLendDays') as Integer

        and:
        messageSource.addMessage('lend.success', LocaleContextHolder.locale, 'The book was successfully lended until {0,date}')

        when:
        LendService.LendResult result = service.lendBook(testDataService.book1, customer)

        then:
        result.success
        result.message == 'The book was successfully lended until ' + formatter.format(now + days)
        result.lend.id
        result.lend.book == testDataService.book1
        result.lend.dateFrom == now
        result.lend.dateTo == now + days
        result.lend.customer == customer
    }

}
