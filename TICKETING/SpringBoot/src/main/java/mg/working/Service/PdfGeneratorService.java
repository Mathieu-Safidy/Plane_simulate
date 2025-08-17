package mg.working.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import mg.working.Dto.ReservationDto;
import mg.working.model.PlaceReserve;
import mg.working.model.Reservation;
import mg.working.model.ReservationMere;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfGeneratorService {

    private final SpringTemplateEngine templateEngine;
    private final ReservationService reservationService;
    private final PlaceReserveService placeReserveService;

    public PdfGeneratorService(SpringTemplateEngine templateEngine, ReservationService reservationService, PlaceReserveService placeReserveService) {
        this.templateEngine = templateEngine;
        this.reservationService = reservationService;
        this.placeReserveService = placeReserveService;
    }

    public byte[] generatePdf(Reservation reservation) throws Exception {
        Context context = new Context();
        ByteArrayOutputStream outputStream  = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        List<ReservationDto> reservationdto = reservationService.coutReservationByType(reservation.getIdReservation());
        List<PlaceReserve> placereserve = placeReserveService.getAllPlaceReserveByReservation(reservation.getIdReservation());
        context.setVariable("reservation", reservation);
        context.setVariable("reservationDetail", reservationdto);
        context.setVariable("placeDetail", placereserve);
        String htmlcontent = templateEngine.process("Detail", context).trim();
        htmlcontent = htmlcontent.replaceAll("^\\uFEFF", "");
        System.out.println((int)htmlcontent.charAt(0));
        builder.useFastMode();
        System.out.println(htmlcontent);
        builder.withHtmlContent(htmlcontent,null);
        builder.toStream(outputStream);
        builder.run();
        return outputStream.toByteArray();
    }

}
