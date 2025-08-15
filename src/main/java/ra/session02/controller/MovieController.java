package ra.session02.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ra.session02.model.dto.MovieDTO;
import ra.session02.model.entity.Movie;
import ra.session02.service.MovieService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public String getMovies(Model model,
                            @RequestParam(value = "keyword", required = false) String keyword,
                            @RequestParam(name = "page", defaultValue = "0") int page,
                            @RequestParam(name = "size", defaultValue = "5") int size
    ) {
        // Lấy trang phim theo keyword và phân trang
        Page<Movie> moviePage = movieService.findAll(keyword, page, size);
        // Tạo danh sách số trang
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= moviePage.getTotalPages(); i++) {
            pages.add(i);
        }
        model.addAttribute("movies", moviePage.getContent());
        model.addAttribute("pages", pages);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", pages);
        model.addAttribute("keyword", keyword);
        return "movieList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new MovieDTO());
        return "addMovie";
    }

    @PostMapping("/add")
    public String processAddForm(@Valid @ModelAttribute("movie") MovieDTO movieDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Model model
    ) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "addMovie";
        }
        Movie movie = movieService.addMovie(movieDTO);
        if (movie != null) {
           redirectAttributes.addFlashAttribute("message", "Thêm thành công");
            return "redirect:/movies";
        } else {
            model.addAttribute("message", "Lỗi khi thêm phim");
            model.addAttribute("movie", movieDTO);
            return "addMovie";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable long id, RedirectAttributes redirectAttributes) {
        boolean result= movieService.deleteMovie(id);
        if (result) {
            redirectAttributes.addFlashAttribute("message", "Xóa thành công");
        }else {
            redirectAttributes.addFlashAttribute("message", "Xóa thất bại");
        }
        return "redirect:/movies";
    }
}
