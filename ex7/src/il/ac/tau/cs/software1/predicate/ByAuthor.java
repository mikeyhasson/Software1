package il.ac.tau.cs.software1.predicate;

public class ByAuthor implements Predicate<Book> {
	private char letter;
	
	public ByAuthor(char letter) { // Q2
		this.letter=letter;
	}

	@Override
	public boolean test(Book book) { // Q2
		String author = book.getAuthor();
		if (author == null)
			return false;
		char firstLetter= Character.toLowerCase(author.charAt(0));
		if (firstLetter == letter)
			return true;
		return false;
	}
}