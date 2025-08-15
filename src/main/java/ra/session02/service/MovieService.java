package ra.session02.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ra.session02.model.dto.MovieDTO;
import ra.session02.model.entity.Movie;
import ra.session02.repository.MovieRepository;


@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> findAll(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword == null || keyword.isEmpty()) {
            return movieRepository.findAll(pageable);
        }else {
            return movieRepository.searchByTitleOrDirector(keyword,pageable);
        }
    }

    public boolean existsByTitle(String title) {
        long count = movieRepository.countByTitle("%" + title + "%");
        return count > 0;
    }

    public Movie convertToMovie(MovieDTO movieDTO) {
        return Movie.builder()
                .title(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .releaseDate(movieDTO.getReleaseDate())
                .rating(movieDTO.getRating())
                .build();
    }

    public Movie addMovie(MovieDTO movieDTO) {
        Movie movie = convertToMovie(movieDTO);
        return movieRepository.save(movie);
    }

    public Movie findById(long id) {
        return movieRepository.findById(id).orElse(null);
    }

    public boolean deleteMovie(long id) {
        Movie movie = findById(id);
        if (movie != null) {
            movieRepository.delete(movie);
            return true;
        } else {
            return false;
        }
    }
}
