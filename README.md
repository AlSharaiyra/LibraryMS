CREATE TABLE member (
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
email VARCHAR(100) UNIQUE NOT NULL,
phone_number VARCHAR(15) NOT NULL UNIQUE,
registration_date TIMESTAMP NOT NULL
);

CREATE TABLE book (
id INT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255) NOT NULL,
author VARCHAR(255) NOT NULL,
isbn VARCHAR(20) UNIQUE NOT NULL,
published_year INT,
available_copies INT NOT NULL
);

CREATE TABLE borrowed_books (
member_id INT NOT NULL,
book_id INT NOT NULL,
FOREIGN KEY (member_id) REFERENCES member(id),
FOREIGN KEY (book_id) REFERENCES book(id)
);



Here’s a use case scenario for an admin to interact with the Library Management System. This will cover operations like managing members, books, and the process of borrowing and returning books. Each use case will include a description of what the admin can do in the app.

Use Case 1: Creating Library Members
Preconditions:

The admin is logged in (this could be handled as part of future user authentication if needed).
The admin has access to the app.
Scenario:

Admin starts the app and is presented with a menu of options.
Admin chooses the option to create a new library member.
The app prompts the admin to enter the following details:
Name
Email
Phone Number
Registration Date (auto-generated or manually input by admin)
The app checks if the email and phone number are unique using the validation mechanism implemented.
If they are unique, the member is added to the database.
If not, the app informs the admin that the email/phone number already exists.
Admin is notified that the member has been successfully added, and the member’s details are stored in the database.
Use Case 2: Updating Member Information
Preconditions:

The admin has an existing member in the library system.
Scenario:

Admin chooses the option to update member information.
The app prompts the admin to enter the member's ID or search for a member by their name or email.
Admin is presented with the current member details.
Admin selects what information they want to update (e.g., name, email, phone number).
Admin enters the updated details.
The app validates the new email and phone number for uniqueness.
The updated member info is saved in the database.
Admin is notified of the successful update.
Use Case 3: Deleting Library Member Profiles
Preconditions:

The admin has access to a member profile in the system.
Scenario:

Admin selects the option to delete a member.
Admin is prompted to provide the member's ID or search by name/email.
The app retrieves the member details and asks the admin to confirm deletion.
If confirmed, the member is removed from the database.
Any borrowed books or related data (if needed) is handled.
Admin is notified that the member has been successfully deleted.
Use Case 4: Adding Books to the Library
Preconditions:

The admin has access to the system.
Scenario:

Admin selects the option to add a new book.
Admin is prompted to enter the book’s details:
Title
Author
ISBN (which is validated for format and uniqueness)
Genre
Published Date
Available Copies
The app checks if the ISBN is unique.
If the ISBN is unique, the book is added to the database.
If the ISBN is already in use, the app notifies the admin and prevents adding the book.
Admin is notified of the successful addition of the book.
Use Case 5: Updating Book Information
Preconditions:

The admin has an existing book in the system.
Scenario:

Admin selects the option to update a book’s information.
The app prompts the admin to enter the book’s ID or search for it by title, author, or ISBN.
Admin is presented with the current book details.
Admin selects what information to update (e.g., title, author, available copies, genre).
The updated book info is saved in the database.
Admin is notified of the successful update.
Use Case 6: Removing Books from the Library
Preconditions:

The admin has an existing book in the library system.
Scenario:

Admin selects the option to remove a book.
Admin is prompted to provide the book’s ID or search by title, author, or ISBN.
The app retrieves the book details and asks the admin to confirm deletion.
If confirmed, the book is removed from the database.
Admin is notified that the book has been successfully removed.
Use Case 7: Borrowing Books
Preconditions:

The admin has access to both books and members in the system.
Scenario:

Admin selects the option to borrow a book.
The app prompts the admin to provide the member’s ID and the book’s ID.
The app checks if the member is eligible to borrow books (e.g., they don’t have overdue books).
The app checks if the book is available (i.e., there are copies left).
If available, the app updates the book’s available copies.
The book is assigned to the member (added to the borrowed books list in the member’s profile).
Admin is notified that the book has been successfully borrowed.
Use Case 8: Returning Books
Preconditions:

The admin has access to both books and members in the system.
Scenario:

Admin selects the option to return a book.
The app prompts the admin to provide the member’s ID and the book’s ID.
The app checks if the book is currently borrowed by the member.
If the book is found in the member’s borrowed books list, the return process proceeds.
If not found, the app notifies the admin that the book wasn’t borrowed by that member.
The app updates the member’s borrowed books list and the book’s available copies.
Admin is notified that the book has been successfully returned.
Use Case 9: Listing All Borrowed Books
Preconditions:

The admin wants to view all borrowed books.
Scenario:

Admin selects the option to list all borrowed books.
The app fetches all books that are currently borrowed by any member.
The list of borrowed books is displayed with relevant details (e.g., member ID, book title, borrow date).
Admin can choose to return any book from this list (uses Use Case 8).
Use Case 10: Search and Sort Books
Preconditions:

The admin wants to search for books based on specific criteria (e.g., title, author, genre).
Scenario:

Admin selects the option to search for books.
Admin is prompted to provide search criteria (e.g., book title or author).
The app displays matching results, sorted by a chosen criteria (e.g., by title or author).
Admin can select a book from the search results to perform other actions like updating or deleting the book.
Additional Use Case Scenarios:
Log All Actions: All actions performed (e.g., adding books, borrowing books, member creation) are logged into a file for audit purposes.
Exception Handling: In all cases, if an error occurs (e.g., database issue, invalid input), the app handles it gracefully by showing a meaningful message and logging the error.


Title: "To Kill a Mockingbird"
Author: Harper Lee
ISBN: 9780061120084
Genre: Fiction
Published Year: 1960
Publisher: J.B. Lippincott & Co.

Title: "1984"
Author: George Orwell
ISBN: 9780451524935
Genre: Dystopian, Science Fiction
Published Year: 1949
Publisher: Secker & Warburg

Title: "The Great Gatsby"
Author: F. Scott Fitzgerald
ISBN: 9780743273565
Genre: Fiction, Classic
Published Year: 1925
Publisher: Charles Scribner's Sons

Title: "Moby-Dick"
Author: Herman Melville
ISBN: 9781503280786
Genre: Adventure, Fiction
Published Year: 1851
Publisher: Harper & Brothers

Title: "Pride and Prejudice"
Author: Jane Austen
ISBN: 9781503290563
Genre: Romance, Fiction
Published Year: 1813
Publisher: T. Egerton

Title: "The Catcher in the Rye"
Author: J.D. Salinger
ISBN: 9780316769488
Genre: Fiction, Coming-of-Age
Published Year: 1951
Publisher: Little, Brown and Company

Title: "The Hobbit"
Author: J.R.R. Tolkien
ISBN: 9780345339683
Genre: Fantasy, Adventure
Published Year: 1937
Publisher: George Allen & Unwin

Title: "The Alchemist"
Author: Paulo Coelho
ISBN: 9780062315007
Genre: Adventure, Fiction
Published Year: 1988
Publisher: HarperOne

Title: "The Lord of the Rings: The Fellowship of the Ring"
Author: J.R.R. Tolkien
ISBN: 9780618574940
Genre: Fantasy
Published Year: 1954
Publisher: George Allen & Unwin

Title: "Brave New World"
Author: Aldous Huxley
ISBN: 9780060850524
Genre: Dystopian, Science Fiction
Published Year: 1932
Publisher: Chatto & Windus