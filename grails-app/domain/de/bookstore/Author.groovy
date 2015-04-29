package de.bookstore

class Author {

    String name
    Date birthday
    List<Book> books = []

    static hasMany = [books: Book]

    static constraints = {
        birthday nullable: true
    }

}
