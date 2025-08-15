package ra.session02.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.session02.model.entity.Movie;


public interface MovieRepository extends JpaRepository<Movie, Long> {
    //Tìm phim theo tên hoặc tên đạo diễn
    @Query("select m from Movie m where m.title like %:keyword%  or m.director like %:keyword%")
    Page<Movie> searchByTitleOrDirector(@Param("keyword") String keyword, Pageable pageable);

    @Query("select count (m) from Movie m where m.title like :title")
    Long countByTitle(@Param("title") String title);

}
