package de.bookstore

class Author {

    String name
    Date birthday
    String nickName
    List<Book> books = []

    static hasMany = [books: Book]

    static constraints = {
        birthday nullable: true
        nickName nullable: true
    }

}
