Library Management System

Overview:
	The Library Management System is a simple client-server application for managing a library's book inventory.
 	It allows users to perform various operations such as viewing all books, adding new books, borrowing books, and returning books.

Components
	Client: The client-side application allows users to interact with the library system through a graphical user interface (GUI).
	Server: The server-side application manages the book data and handles client requests.

Technologies Used
	Java: The project is implemented using Java programming language.
	Swing: The GUI is created using Java Swing.
	MySQL: The server stores book data in a MySQL database.

Setup Instructions
	Server Setup:
		Ensure you have MySQL installed on your system.
		Create a MySQL database named "library" with a table named "books" using the provided SQL script (library.sql).
		Update the MySQL connection details in the ServerData class.
	Client Setup
		Open the project in an IDE (Integrated Development Environment) such as IntelliJ or Eclipse.
		Update the server address and port in the MainClient class.

Running the Application
	Start the server by running the MainServer class.
	Run the MainClient class to start the client application.
	The GUI will appear, allowing you to interact with the Library Management System.

Usage
	View All Books: Click the "Refresh" button to view all books in the library.
	Add Book: Click the "Add Book" button to add a new book to the library.
	Borrow Book: Click the "Borrow Book" button to borrow a book by providing the book ID and student ID.
	Return Book: Click the "Return Book" button to return a borrowed book by providing the book ID.