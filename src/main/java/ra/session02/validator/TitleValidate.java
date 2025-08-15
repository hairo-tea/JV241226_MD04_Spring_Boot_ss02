package ra.session02.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ra.session02.service.MovieService;

@Component
public class TitleValidate implements ConstraintValidator<UniqueTitle, String> {
    @Autowired
    private MovieService movieService;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext context) {
        return !movieService.existsByTitle(s) ;
    }
}
