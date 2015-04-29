package de.bookstore

import grails.rest.Resource

@Resource(uri = '/v1/book')
class Book {

    long quantity
    String title
    String ISBN
    Author author

    static constraints = {
    }

}
