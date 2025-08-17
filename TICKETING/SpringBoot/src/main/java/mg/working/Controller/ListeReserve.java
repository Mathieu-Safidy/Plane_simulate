package mg.working.Controller;

import jakarta.servlet.http.HttpServletRequest;
import mg.working.Dto.ReservationDto;
import mg.working.Service.PlaceReserveService;
import mg.working.Service.ReservationMereService;
import mg.working.Service.ReservationService;
import mg.working.Service.UserService;
import mg.working.model.PlaceReserve;
import mg.working.model.Reservation;
import mg.working.model.ReservationMere;
import mg.working.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
public class ListeReserve {

    private final UserService userService;
    private final ReservationService reservationService;
    private final ReservationMereService reservationMereService;
    private final PlaceReserveService placeReserveService;

    public ListeReserve(UserService userService, ReservationService reservationService, ReservationMereService reservationMereService , PlaceReserveService placeReserveService) {
        this.userService = userService;
        this.reservationService = reservationService;
        this.reservationMereService = reservationMereService;
        this.placeReserveService = placeReserveService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<User> users = userService.userList();
        model.addAttribute("users", users);
        return "Home";
    }

    @GetMapping("/list/{id}")
    public String listreservation(@PathVariable("id") String id,Model model, HttpServletRequest request) {
        Optional<User> optuser = userService.findByid(id);

        String referer = request.getHeader("Referer");
        if (optuser.isPresent()) {
            // List<ReservationMere> reservationMereList  = reservationMereService.findByIdUser(id);
           List<Reservation> listreservation = reservationService.listReservations(id);
            // model.addAttribute("listreservationmere", reservationMereList);
//            List<ReservationDto> reservationdto = reservationService.coutReservationByType(id);
            model.addAttribute("listreservation", listreservation);
//            model.addAttribute("reservationDetail", reservationdto);
            model.addAttribute("referer", referer);
        }

        return "Liste";
    }

    @GetMapping("/detail/reservation/{id}")
    public String detailReservation(@PathVariable("id") String id, Model model, HttpServletRequest request) {
        Optional<Reservation> reservation = reservationService.getByIdService(id);
        String referer = request.getHeader("Referer");
        if (reservation.isPresent()) {
            List<ReservationDto> reservationdto = reservationService.coutReservationByType(id);
            List<PlaceReserve> placereserve = this.placeReserveService.getAllPlaceReserveByReservation(id);
            model.addAttribute("reservationDetail", reservationdto);
            model.addAttribute("reservation", reservation.get());
            model.addAttribute("placeDetail", placereserve);
            model.addAttribute("referer",referer);
            return "Detail";
        }
        return "redirect:"+referer;
    }

}
