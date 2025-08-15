package ra.session02.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.session02.model.entity.Movie;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    //Tìm phim theo tên hoặc tên đạo diễn
    @Query("select m from Movie m where m.title like %:keyword%  or m.director like %:keyword%")
    Page<Movie> searchByTitleOrDirector(@Param("keyword") String keyword, Pageable pageable);

    //hiển thị danh sách phim có phân trang
    @Query("select count (m) from Movie m where m.title like :title")
    Long countByTitle(@Param("title") String title);

    //Hiển thị top 3 phim có rating cao nhất.
    List<Movie> findTop3ByOrderByRatingDesc();

    //Hiển thị danh sách phim phát hành trong tháng này.
    @Query("select m from Movie m where MONTH (m.releaseDate) = MONTH (CURRENT DATE ) and YEAR(m.releaseDate) = year (CURRENT DATE )")
    List<Movie> findMoviesThisMonth(int month,int year);

    //lấy rating cao nhât
    @Query("select max (m.rating) from Movie m")
    Double findMaxRating();

    //danh sách đạo diễn có rating cao nhất
    @Query("select m.director from Movie m where m.rating = :rating")
    List<String> findDirectorsByRating(Double rating);

    //lấy phim theo danh sách đạo diễn
    List<Movie>findByDirector(String director);
}
