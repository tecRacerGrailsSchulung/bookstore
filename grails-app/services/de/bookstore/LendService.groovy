package de.bookstore

import grails.core.GrailsApplication
import grails.transaction.Transactional
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder

@Transactional
class LendService {

    DateService dateService
    MessageSource messageSource
    GrailsApplication grailsApplication

    static class LendResult {
        boolean success
        String message
        Lend lend
    }

    LendResult lendBook(Book book, Customer customer) {

        if (Lend.countByBook(book)) {
            // ...
        }

        Date now = dateService.now()
        Lend lend = new Lend(book: book, customer: customer)
        lend.dateFrom = now
        lend.dateTo = now + (grailsApplication.config.getProperty('de.bookstore.defaultLendDays') as Integer)
        lend.save()

        return new LendResult(
                success: lend.validate(),
                message: messageSource.getMessage('lend.success', [lend.dateTo] as Object[], LocaleContextHolder.locale),
                lend: lend)
    }

}
