import java.util.ArrayList;
import java.util.List;

// IFilm Interface
interface IFilm {
    String getTitle();
    String getDirector();
    int getYear();
}

// Film Class
class Film implements IFilm {
    private String title;
    private String director;
    private int year;

    public Film(String title, String director, int year) {
        this.title = title;
        this.director = director;
        this.year = year;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDirector() {
        return director;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return title + " (" + director + ", " + year + ")";
    }
}

// IFilmLibrary Interface
interface IFilmLibrary {
    void addFilm(IFilm film);
    void removeFilm(String title);
    List<IFilm> getFilms();
    List<IFilm> searchFilms(String query);
    int getTotalFilmCount();
}

// FilmLibrary Class
class FilmLibrary implements IFilmLibrary {
    private List<IFilm> films;

    public FilmLibrary() {
        this.films = new ArrayList<>();
    }

    @Override
    public void addFilm(IFilm film) {
        films.add(film);
    }

    @Override
    public void removeFilm(String title) {
        for (IFilm film : films) {
            if (film.getTitle().equals(title)) {
                films.remove(film);
                System.out.println("Removed Film: " + film);
                return;
            }
        }
        System.out.println("Film with title " + title + " not found.");
    }

    @Override
    public List<IFilm> getFilms() {
        return films;
    }

    @Override
    public List<IFilm> searchFilms(String query) {
        List<IFilm> results = new ArrayList<>();
        for (IFilm film : films) {
            if (film.getTitle().contains(query) || film.getDirector().contains(query)) {
                results.add(film);
            }
        }
        return results;
    }

    @Override
    public int getTotalFilmCount() {
        return films.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (IFilm film : films) {
            result.append(film).append("\n");
        }
        return result.toString().trim();
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        // Create films
        Film film1 = new Film("HarryPotter", "DavidYates", 2007);
        Film film2 = new Film("TheLordOfTheRings", "PeterJackson", 2001);

        // Create a film library
        FilmLibrary filmLibrary = new FilmLibrary();

        // Add films to the library
        filmLibrary.addFilm(film1);
        filmLibrary.addFilm(film2);

        // Output total film count
        System.out.println("Total Film Count: " + filmLibrary.getTotalFilmCount());

        // Search for a film by director
        List<IFilm> searchResults = filmLibrary.searchFilms("DavidYates");
        System.out.println("Search Results for DavidYates:");
        for (IFilm film : searchResults) {
            System.out.println(film);
        }

        // Remove a film
        filmLibrary.removeFilm("TheLordOfTheRings");

        // Display remaining films
        System.out.println("All Films:");
        System.out.println(filmLibrary);
    }
}