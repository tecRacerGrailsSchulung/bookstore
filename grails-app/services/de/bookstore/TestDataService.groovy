package de.bookstore

class TestDataService {

    Book book1

    void createTestData() {

        book1 = new Book(
                quantity: 3,
                title: 'Bobo Siebenschläfer: Geschichten für ganz Kleine',
                ISBN: '978-3499203688')
        book1.save()

        Author author1 = new Author(name: 'Markus Osterwalder')
        author1.addToBooks(book1)
        author1.save()
    }

}
