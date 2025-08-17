package mg.working.Controller;

import mg.working.Dto.ReservationDto;
import mg.working.Service.PdfGeneratorService;
import mg.working.Service.ReservationMereService;
import mg.working.Service.ReservationService;
import mg.working.model.PlaceReserve;
import mg.working.model.Reservation;
import mg.working.model.ReservationMere;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PdfController {

    private final ReservationMereService reservationMereService;
    private final PdfGeneratorService pdfGeneratorService;
    private final ReservationService reservationService;

    public PdfController(ReservationMereService reservationMereService, PdfGeneratorService pdfGeneratorService, ReservationService reservationService) {
        this.reservationMereService = reservationMereService;
        this.pdfGeneratorService = pdfGeneratorService;
        this.reservationService = reservationService;
    }

//    @GetMapping("/pdf/{id}")
//    public ResponseEntity<?> pdf(@PathVariable String id) {
//        Optional<ReservationMere> reservationMere = reservationMereService.findBydId(id);
//        try {
//            if (reservationMere.isPresent()) {
//                List<Reservation> reservations = reservationMere.get().getReservations();
//                byte[] pdfBytes = pdfGeneratorService.generatePdf(reservationMere.get());
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservation_" + id + ".pdf")
//                        .contentType(MediaType.APPLICATION_PDF)
//                        .body(pdfBytes);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ResponseEntity.badRequest().body("Erreur generation  de pdf pour la reservation : " + id + "\n");
//    }
    @PostMapping("/pdf")
    public ResponseEntity<?> pdf(@RequestBody Map<String, Object> body) {
        String id = (String)body.get("id");
        Optional<Reservation> reservation = reservationService.getByIdService(id);
        try {
            if (reservation.isPresent()) {
                byte[] pdfBytes = pdfGeneratorService.generatePdf(reservation.get());
                System.out.println("fichier"+pdfBytes);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservation_" + id + ".pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdfBytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Erreur generation  de pdf pour la reservation : " + id + "\n");
    }
}
